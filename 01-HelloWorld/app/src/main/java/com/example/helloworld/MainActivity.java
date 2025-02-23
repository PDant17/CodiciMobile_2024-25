package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
    }

    public void pulsantePremuto(View v) {
        int r = rand.nextInt(255) + 1 ;
        int g = rand.nextInt(255) + 1 ;
        int b = rand.nextInt(255) + 1 ;
        tv.setBackgroundColor(Color.rgb(r,g,b));
    }
}