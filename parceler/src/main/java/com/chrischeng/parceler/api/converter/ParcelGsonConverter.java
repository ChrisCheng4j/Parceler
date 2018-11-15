package com.chrischeng.parceler.api.converter;

import com.google.gson.Gson;

public class ParcelGsonConverter {

    private static Gson gson = new Gson();

    public static Object convert(Object obj, Class cls) {
        return obj instanceof String ? gson.fromJson((String) obj, cls): null;
    }
}
