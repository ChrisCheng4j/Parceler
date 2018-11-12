package com.chrischeng.parceler.compiler;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.compiler.generator.ParcelArgCodeGenerator;
import com.chrischeng.parceler.compiler.parser.ParcelArgElementParser;
import com.chrischeng.parceler.compiler.util.CompileTools;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

public class CompileProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        CompileTools.get().init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
        try {
            ParcelArgCodeGenerator.generate(ParcelArgElementParser.parse(roundEnv));
        } catch (IOException e) {
            //
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(ParcelerArg.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
