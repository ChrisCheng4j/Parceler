package com.chrischeng.parceler.compiler;

public class Constants {

    public static final String PACKAGE_NAME = "com.chrischeng.parceler.api";

    public static final String GENERATE_CLASS_ARG_INJECTOR_SUFFIX_NAME = "_ArgInjector";
    public static final String GENERATE_METHOD_INJECT_NAME = "inject";

    public static final String PACKAGE_ARG_INJECTOR_NAME = PACKAGE_NAME + ".creator";
    public static final String INTERFACE_ARG_INJECTOR_SIMPLE_NAME = "IParcelArgInjector";
    public static final String INTERCE_ARG_INJECTOR_FULL_NAME = PACKAGE_ARG_INJECTOR_NAME + "." + INTERFACE_ARG_INJECTOR_SIMPLE_NAME;

    public static final String PACKAGE_PARCEL_HELPER_NAME = PACKAGE_NAME + ".helper";
    public static final String CLASS_PARCEL_HELPER_SIMPLE_NAME = "ParcelHelper";

    public static final String METHOD_PARENT_INJECTOR_NAME = "getParentArgInjector";
    public static final String PARAM_TARGET_NAME = "target";
    public static final String PARAM_BUNDLE_NAME = "bundle";
    public static final String METHOD_BUNDLE_GET_NAME = "get";
}
