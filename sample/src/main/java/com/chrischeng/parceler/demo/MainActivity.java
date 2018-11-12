package com.chrischeng.parceler.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

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
                intent.putExtra("year", String.valueOf(calendar.get(Calendar.YEAR)));
                intent.putExtra("m", calendar.get(Calendar.MONTH));
                startActivity(intent);
            }
        });
    }
}