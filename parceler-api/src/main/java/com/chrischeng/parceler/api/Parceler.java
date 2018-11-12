package com.chrischeng.parceler.api;

import android.content.Intent;
import android.os.Bundle;

@SuppressWarnings("unchecked")
public class Parceler {

    public static void injectArgs(Object target, Intent intent) {
        if (intent == null)
            return;

        injectArgs(target, intent.getExtras());
    }

    public static void injectArgs(Object target, Bundle bundle) {
        if (target == null || bundle == null)
            return;

        IParcelArgInjector injector = getArgInjectorClass(target.getClass());
        if (injector != null)
            injector.inject(target, bundle);
    }

    private static IParcelArgInjector getArgInjectorClass(Class targetClass) {
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

    public static IParcelArgInjector getParentArgInjector(Class targetClass) {
        Class parentClass = targetClass.getSuperclass();

        String parentClassName = parentClass.getCanonicalName();
        for (String prefix : ParcelConstants.PACKAGE_FILTER_PREFIX) {
            if (parentClassName.contains(prefix))
                return null;
        }

        return getArgInjectorClass(parentClass);
    }
}
