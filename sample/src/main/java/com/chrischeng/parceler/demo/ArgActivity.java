package com.chrischeng.parceler.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.chrischeng.parceler.annotation.ParcelerArg;
import com.chrischeng.parceler.api.Parceler;
import com.chrischeng.parceler.demo.model.User;

public class ArgActivity extends AppCompatActivity {

    static final String KEY_DATE = "date";
    static final String KEY_USER1 = "user1";
    static final String KEY_USER2 = "user2";
    static final String KEY_URI = "uri";

    @ParcelerArg
    String date;
    @ParcelerArg
    User user1;
    @ParcelerArg
    User user2;
    @ParcelerArg
    Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arg);

        Parceler.injectArgs(this, getIntent());
        ((TextView) findViewById(R.id.tv_date)).setText(date);
        ((TextView) findViewById(R.id.tv_user1)).setText(user1.name + ":" + user1.age);
        ((TextView) findViewById(R.id.tv_user2)).setText(user2.name + ":" + user2.age);
        ((TextView) findViewById(R.id.tv_uri)).setText(uri.toString());
    }
}
