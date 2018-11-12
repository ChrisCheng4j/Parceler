package com.chrischeng.parceler.compiler.parser;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.compiler.model.ParcelArgFieldInfo;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

class ParcelArgFieldParser {

    static List<ParcelArgFieldInfo> parse(TypeElement typeElement) {
        List<ParcelArgFieldInfo> fieldInfos = new ArrayList<>();

        List<VariableElement> elements = getVariableElements(typeElement);
        for (VariableElement element : elements)
            fieldInfos.add(getFieldInfo(element));

        return fieldInfos;
    }

    private static List<VariableElement> getVariableElements(TypeElement typeElement) {
        List<VariableElement> variableElements = new ArrayList<>();

        List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
        for (Element element : enclosedElements) {
            if (element.getKind() == ElementKind.FIELD)
                variableElements.add((VariableElement) element);
        }

        return variableElements;
    }

    private static ParcelArgFieldInfo getFieldInfo(VariableElement element) {
        ParcelerArg arg = element.getAnnotation(ParcelerArg.class);
        return new ParcelArgFieldInfo(element.asType(), element.getSimpleName().toString(), arg.value());
    }
}
