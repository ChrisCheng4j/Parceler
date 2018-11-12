package com.chrischeng.parceler.compiler.model;

import com.chrischeng.parceler.compiler.util.TextUtils;

import javax.lang.model.type.TypeMirror;

public class ParcelArgFieldInfo {

    public TypeMirror typeMirror;
    public String name;
    public String key;

    public ParcelArgFieldInfo(TypeMirror typeMirror, String name, String key) {
        this.typeMirror = typeMirror;
        this.name = name;
        this.key = !TextUtils.isEmpty(key) ? key : name;
    }
}
