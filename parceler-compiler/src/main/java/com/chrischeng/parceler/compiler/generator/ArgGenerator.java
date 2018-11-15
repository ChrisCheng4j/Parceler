package com.chrischeng.parceler.compiler.generator;

import com.chrischeng.parceler.compiler.Constants;
import com.chrischeng.parceler.compiler.exception.ParcelerInternalException;
import com.chrischeng.parceler.compiler.model.ArgFieldInfo;
import com.chrischeng.parceler.compiler.util.CompileTools;
import com.chrischeng.parceler.compiler.util.TextUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

public class ArgGenerator {

    private static String methodName;
    private static String parentInjectFormat;

    public static void generate(Map<TypeElement, List<ArgFieldInfo>> args) throws IOException {
        if (args == null || args.isEmpty())
            return;

        Set<TypeElement> typeElements = args.keySet();
        for (TypeElement typeElement : typeElements) {
            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(getClassSimpleName(typeElement))
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addSuperinterface(ParameterizedTypeName.get(ClassName.get(Constants.PACKAGE_ARG_INJECTOR_NAME, Constants.INTERFACE_ARG_INJECTOR_SIMPLE_NAME), ClassName.get(typeElement)));
            typeBuilder.addMethod(generateInjectMethod(typeElement, args.get(typeElement)));

            JavaFile.builder(getPackageName(typeElement), typeBuilder.build())
                    .addFileComment("This class is generated by Parceler. Do not modify!")
                    .build()
                    .writeTo(CompileTools.get().getFiler());
        }
    }

    private static String getClassSimpleName(TypeElement typeElement) {
        return typeElement.getSimpleName() + Constants.GENERATE_CLASS_ARG_INJECTOR_SUFFIX_NAME;
    }

    private static MethodSpec generateInjectMethod(TypeElement typeElement, List<ArgFieldInfo> fieldInfos) {
        ClassName typeClassName = ClassName.get(typeElement);
        ClassName parcelHelperClassName = ClassName.get(Constants.PACKAGE_PARCEL_HELPER_NAME, Constants.CLASS_PARCEL_HELPER_SIMPLE_NAME);

        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(Constants.GENERATE_METHOD_INJECT_NAME)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(typeClassName, Constants.PARAM_TARGET_NAME).build())
                .addParameter(ParameterSpec.builder(ClassName.get("android.os", "Bundle"), Constants.PARAM_BUNDLE_NAME).build())
                .returns(TypeName.VOID);

        TypeElement interfaceElement = CompileTools.get().getElementUtils().getTypeElement(Constants.INTERCE_ARG_INJECTOR_FULL_NAME);
        ExecutableElement executableElement = getOverrideMethod(interfaceElement, Constants.GENERATE_METHOD_INJECT_NAME);

        if (TextUtils.isEmpty(methodName))
            methodName = executableElement.getSimpleName().toString();

        methodBuilder.addStatement("$T injector = $T." + Constants.METHOD_PARENT_INJECTOR_NAME + "($T.class)",
                ClassName.get(Constants.PACKAGE_ARG_INJECTOR_NAME, Constants.INTERFACE_ARG_INJECTOR_SIMPLE_NAME),
                parcelHelperClassName,
                typeClassName);

        methodBuilder.beginControlFlow("if (injector != null)");
        if (TextUtils.isEmpty(parentInjectFormat))
            parentInjectFormat = "injector" + "." + methodName + getMethodParamsFormat();
        methodBuilder.addStatement(parentInjectFormat, parcelHelperClassName, typeClassName);
        methodBuilder.endControlFlow();

        CodeBlock newLineblock = CodeBlock.builder().add("\r\n").build();
        methodBuilder.addCode(newLineblock);

        methodBuilder.addStatement("Object obj");

        StringBuilder statementFormat;
        for (ArgFieldInfo fieldInfo : fieldInfos) {
            methodBuilder.addCode(newLineblock);

            methodBuilder.addStatement("obj = " + Constants.PARAM_BUNDLE_NAME + "." + Constants.METHOD_BUNDLE_GET_NAME + "($S)", fieldInfo.key);

            methodBuilder.beginControlFlow("if (obj != null)");

            TypeMirror typeMirror = fieldInfo.typeMirror;

            statementFormat = new StringBuilder();
            statementFormat.append(Constants.PARAM_TARGET_NAME + "." + fieldInfo.name);
            statementFormat.append(" = ");
            statementFormat.append("(" + "$T" + ") ");

            if (typeMirror.getKind().isPrimitive() || typeMirror.toString().equals("java.lang.String")) {
                statementFormat.append("obj");
                methodBuilder.addStatement(statementFormat.toString(), typeMirror);
            } else {
                statementFormat.append("(");
                statementFormat.append("$T.convert(obj, $T.class)");
                statementFormat.append(")");
                methodBuilder.addStatement(statementFormat.toString(),
                        typeMirror,
                        parcelHelperClassName,
                        ClassName.get(typeMirror));
            }

            methodBuilder.endControlFlow();
        }

        return methodBuilder.build();
    }

    private static ExecutableElement getOverrideMethod(TypeElement typeElement, String methodName) {
        if (typeElement != null) {
            List<? extends Element> enclosedElement = typeElement.getEnclosedElements();
            if (enclosedElement != null && !enclosedElement.isEmpty()) {
                for (Element element : enclosedElement) {
                    if (element.getKind() != ElementKind.METHOD || !element.getSimpleName().toString().equals(methodName))
                        continue;

                    return (ExecutableElement) element;
                }
            }
        }

        throw new ParcelerInternalException(String.format("Could not found method %s when getOverrideMethod during generate code.", methodName));
    }

    private static String getMethodParamsFormat() {
        return "(" + Constants.PARAM_TARGET_NAME + ", " + Constants.PARAM_BUNDLE_NAME + ")";
    }

    private static String getPackageName(TypeElement typeElement) {
        Element element = typeElement.getEnclosingElement();
        if (element.getKind() == ElementKind.PACKAGE)
            return ((PackageElement) element).getQualifiedName().toString();

        return getPackageName((TypeElement) element);
    }
}