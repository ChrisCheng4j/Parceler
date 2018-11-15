package com.chrischeng.parceler.compiler.parser;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.compiler.model.ArgFieldInfo;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

class ArgFieldParser {

    static List<ArgFieldInfo> parse(TypeElement typeElement) {
        List<ArgFieldInfo> fieldInfos = new ArrayList<>();

        List<VariableElement> elements = getArgVariableElements(typeElement);
        for (VariableElement element : elements)
            fieldInfos.add(getFieldInfo(element));

        return fieldInfos;
    }

    private static List<VariableElement> getArgVariableElements(TypeElement typeElement) {
        List<VariableElement> variableElements = new ArrayList<>();

        List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
        for (Element element : enclosedElements) {
            if (element.getKind() == ElementKind.FIELD && element.getAnnotation(ParcelerArg.class) != null)
                variableElements.add((VariableElement) element);
        }

        return variableElements;
    }

    private static ArgFieldInfo getFieldInfo(VariableElement element) {
        ParcelerArg arg = element.getAnnotation(ParcelerArg.class);
        return new ArgFieldInfo(element.asType(), element.getSimpleName().toString(), arg.value());
    }
}
