package com.chrischeng.parceler.demo.util;

import com.google.gson.Gson;

public class JsonUtil {

    private static Gson gson = new Gson();

    public static String convert(Object obj) {
        return gson.toJson(obj);
    }
}
