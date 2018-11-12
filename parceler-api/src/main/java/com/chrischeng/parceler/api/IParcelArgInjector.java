package com.chrischeng.parceler.api;

import android.os.Bundle;

public interface IParcelArgInjector<T> {

    void inject(T target, Bundle bundle);
}
