package com.chrischeng.parceler.api.converter;

import android.net.Uri;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class ParcelGsonUriTypeAdapter extends TypeAdapter<Uri> {

    @Override
    public Uri read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        String nextString = in.nextString();
        return "null".equals(nextString) ? null : Uri.parse(nextString);
    }

    @Override
    public void write(JsonWriter out, Uri value) throws IOException {
        out.value(value == null ? null : value.toString());
    }
}
