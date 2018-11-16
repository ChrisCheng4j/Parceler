package com.chrischeng.parceler.api.converter;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class ParcelJsonConverter {

    private static Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Uri.class, new ParcelGsonUriTypeAdapter())
            .create();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static Object fromJson(Object obj, Type type) {
        return obj instanceof String ? gson.fromJson((String) obj, type) : null;
    }
}
