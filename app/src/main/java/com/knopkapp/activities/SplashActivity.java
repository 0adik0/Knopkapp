package com.knopkapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.knopkapp.R;
import com.knopkapp.customviews.TypeWriter;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private TypeWriter typeWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        typeWriter = findViewById(R.id.typeWriterTextView);
        typeWriter.setCharacterDelay(100);
        typeWriter.animateText("- в одной кнопке");

        new Handler().postDelayed(() -> {
            typeWriter.appendText(" - твой мир -");
        }, "- твой мир -".length() * 160);

        new Handler().postDelayed(() -> {
            ImageView imageView3 = findViewById(R.id.imageView3);
            imageView3.setImageResource(R.drawable.btnofficon);
        }, 2000);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 4000);
    }
}
