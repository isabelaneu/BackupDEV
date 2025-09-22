package com.aula.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void ex1(View view) {
        Intent intent = new Intent(this, exercicio1.class);
        startActivity(intent);
    }

    public void ex2(View view) {
        Intent intent = new Intent(this, exercicio2.class);
        startActivity(intent);
    }

    public void ex3(View view) {
        Intent intent = new Intent(this, netflix.class);
        startActivity(intent);
    }

    public void ViewBing(View view) {
        Intent intent = new Intent(this, ScreenJavaViewBing.class);
        startActivity(intent);
    }
}