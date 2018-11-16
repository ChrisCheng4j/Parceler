package com.chrischeng.parceler.api.helper;

import com.chrischeng.parceler.api.ParcelConstants;
import com.chrischeng.parceler.api.converter.ParcelJsonConverter;
import com.chrischeng.parceler.api.creator.IParcelArgInjector;

public class ParcelHelper {

    public static IParcelArgInjector getParentArgInjector(Class targetClass) {
        Class parentClass = targetClass.getSuperclass();

        String parentClassName = parentClass.getCanonicalName();
        for (String prefix : ParcelConstants.PACKAGE_FILTER_PREFIX) {
            if (parentClassName.contains(prefix))
                return null;
        }

        return getArgInjectorClass(parentClass);
    }

    public static IParcelArgInjector getArgInjectorClass(Class targetClass) {
        String injectorName = targetClass.getCanonicalName() + ParcelConstants.GENERATE_CLASS_ARG_INJECTOR_SUFFIX_NAME;
        try {
            Class cls = Class.forName(injectorName);
            return (IParcelArgInjector) cls.newInstance();
        } catch (ClassNotFoundException e) {
            //
        } catch (IllegalAccessException e) {
            //
        } catch (InstantiationException e) {
            //
        }

        return null;
    }

    public static Object convert(Object obj, Class cls) {
        return cls.isInstance(obj) ? obj : ParcelJsonConverter.fromJson(obj, cls);
    }
}
