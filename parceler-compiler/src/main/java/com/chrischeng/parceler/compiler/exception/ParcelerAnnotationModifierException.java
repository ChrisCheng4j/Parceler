package com.chrischeng.parceler.compiler.exception;

import javax.lang.model.element.Name;

public class ParcelerAnnotationModifierException extends RuntimeException {

    public ParcelerAnnotationModifierException(Name simpleName, Class annotation, String modifier) {
        super(String.format("The element %1s annotated with %2s must not be modifiered by %3s.", simpleName.toString(), annotation.getSimpleName(), modifier));
    }
}
