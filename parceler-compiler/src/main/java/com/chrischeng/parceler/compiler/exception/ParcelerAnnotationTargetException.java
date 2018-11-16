package com.chrischeng.parceler.compiler.exception;

import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;

public class ParcelerAnnotationTargetException extends RuntimeException {

    public ParcelerAnnotationTargetException(Name simpleName, Class annotation, ElementKind kind) {
        super(String.format("The element %1s annotated with %2s must be a %3s.", simpleName.toString(), annotation.getSimpleName(), kind.name()));
    }
}
