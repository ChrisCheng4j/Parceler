package com.chrischeng.parceler.api.creator;

import android.os.Bundle;

public interface IParcelArgInjector<T> {

    void inject(T target, Bundle bundle);
}
