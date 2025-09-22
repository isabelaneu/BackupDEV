package com.aula.exsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {





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
        Intent intent = new Intent(this, Ex1Splash.class);
                startActivity(intent);
    }

    public void ex2(View view) {
        Intent intent = new Intent(this, Ex2Splash.class);
        startActivity(intent);
    }

    public void ppt(View view) {
        Intent intent = new Intent(this, PedraPapelTesouraSplash.class);
        startActivity(intent);
    }

    public void disney(View view) {
        Intent intent = new Intent(this, DisneyAt.class);
        startActivity(intent);
    }

    public void bundle(View view) {
        Intent intent = new Intent(this, TelaBundle.class);
        startActivity(intent);
    }
}