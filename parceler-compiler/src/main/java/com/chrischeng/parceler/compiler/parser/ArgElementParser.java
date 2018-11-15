package com.chrischeng.parceler.compiler.parser;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.compiler.exception.ParcelerAnnotationTargetException;
import com.chrischeng.parceler.compiler.model.ArgFieldInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

public class ArgElementParser {

    public static Map<TypeElement, List<ArgFieldInfo>> parse(RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ParcelerArg.class);
        if (elements == null || elements.isEmpty())
            return null;

        Map<TypeElement, List<ArgFieldInfo>> typeFields = new HashMap<>();

        TypeElement typeElement;
        for (Element element : elements) {
            if (element.getKind() != ElementKind.FIELD)
                throw new ParcelerAnnotationTargetException(element.getSimpleName(), ParcelerArg.class, ElementKind.FIELD);

            typeElement = (TypeElement) element.getEnclosingElement();

            if (typeFields.containsKey(typeElement))
                continue;

            typeFields.put(typeElement, ArgFieldParser.parse(typeElement));
        }

        return typeFields;
    }
}
