package com.aula.listafavoritos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button noticias;
    Button redes;

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

        noticias = findViewById(R.id.noticias);
        redes = findViewById(R.id.redes);

        noticias.setOnClickListener(v -> {
            Intent rota = new Intent(this, Noticias.class );
            startActivity(rota);
        });

        redes.setOnClickListener(v -> {
            Intent rota = new Intent(this, RedesSociais.class );
            startActivity(rota);
        });
    }
}