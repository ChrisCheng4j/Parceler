package com.chrischeng.parceler.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chrischeng.parceler.api.converter.ParcelJsonConverter;
import com.chrischeng.parceler.demo.model.User;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ArgActivity.class);

                Calendar calendar = Calendar.getInstance();
                String date = String.format(Locale.getDefault(), "%1d.%2d.%3d",
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE));

                intent.putExtra(ArgActivity.KEY_DATE, date);
                intent.putExtra(ArgActivity.KEY_USER1, new User("Chris", 26));
                intent.putExtra(ArgActivity.KEY_USER2, ParcelJsonConverter.toJson(new User("Cheng", 27)));
                intent.putExtra(ArgActivity.KEY_URI, ParcelJsonConverter.toJson(Uri.parse("router://path")));

                startActivity(intent);
            }
        });
    }
}
