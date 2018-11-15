package com.chrischeng.parceler.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.api.Parceler;
import com.chrischeng.parceler.demo.model.User;

public class ArgActivity extends AppCompatActivity {

    @ParcelerArg
    String year;
    @ParcelerArg("m")
    int month;
    @ParcelerArg
    User user1;
    @ParcelerArg
    User user2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parceler.injectArgs(this, getIntent());
        Log.d("aaa", "year:" + year);
        Log.d("aaa", "month:" + month);
        Log.d("aaa", "name1:" + user1.name);
        Log.d("aaa", "age1:" + user1.age);
        Log.d("aaa", "name2:" + user2.name);
        Log.d("aaa", "age2:" + user2.age);
    }
}
