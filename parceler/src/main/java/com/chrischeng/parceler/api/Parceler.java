package com.chrischeng.parceler.api;

import android.content.Intent;
import android.os.Bundle;

import com.chrischeng.parceler.api.creator.IParcelArgInjector;
import com.chrischeng.parceler.api.helper.ParcelHelper;

public class Parceler {

    public static void injectArgs(Object target, Intent intent) {
        if (intent == null)
            return;

        injectArgs(target, intent.getExtras());
    }

    @SuppressWarnings("unchecked")
    public static void injectArgs(Object target, Bundle bundle) {
        if (target == null || bundle == null)
            return;

        IParcelArgInjector injector = ParcelHelper.getArgInjectorClass(target.getClass());
        if (injector != null)
            injector.inject(target, bundle);
    }
}
