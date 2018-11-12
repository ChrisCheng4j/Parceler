package com.chrischeng.parceler.compiler;

public class ParcelConstants {

    public static final String PACKAGE_NAME = "com.chrischeng.parceler.api";

    public static final String GENERATE_CLASS_ARG_INJECTOR_SUFFIX_NAME = "_ArgInjector";
    public static final String GENERATE_METHOD_INJECT_NAME = "inject";

    public static final String INTERFACE_ARG_INJECTOR_SIMPLE_NAME = "IParcelArgInjector";
    public static final String INTERCE_ARG_INJECTOR_FULL_NAME = PACKAGE_NAME + "." + INTERFACE_ARG_INJECTOR_SIMPLE_NAME;
    public static final String CLASS_PARCELER_SIMPLE_NAME = "Parceler";

    public static final String METHOD_PARENT_INJECTOR_NAME = "getParentArgInjector";
    public static final String PARAM_TARGET_NAME = "target";
    public static final String PARAM_BUNDLE_NAME = "bundle";
    public static final String METHOD_BUNDLE_GET_NAME = "get";
}
