package com.example.sam.leceladon_managing_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Leceladon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leceladon);
        TextView textView = findViewById(R.id.textView3);
        //TextView textView1 = findViewById(R.id.textView5);
        String resp = getIntent().getStringExtra("response");
        textView.setText(resp);
    }
}
