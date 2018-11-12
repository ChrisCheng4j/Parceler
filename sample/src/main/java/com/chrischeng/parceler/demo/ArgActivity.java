package com.chrischeng.parceler.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.api.Parceler;

public class ArgActivity extends AppCompatActivity {

    @ParcelerArg
    String year;
    @ParcelerArg("m")
    int month;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parceler.injectArgs(this, getIntent());
        Log.d("aaa", "year:" + year);
        Log.d("aaa", "month:" + month);
    }
}
