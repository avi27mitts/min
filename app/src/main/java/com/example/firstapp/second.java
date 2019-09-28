package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class second extends AppCompatActivity {

    TextView tvr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvr = findViewById(R.id.tvr);

        Intent intentThatStartedThis = getIntent();
        //                    name of key with which it was stored, default value that should be returned if key is not found
        int res = intentThatStartedThis.getIntExtra("result",0);

        tvr.setText(String.valueOf(res));




    }
}
