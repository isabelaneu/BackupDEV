package com.aula.listafavoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Noticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_noticias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void not1(View view) {
        Bundle opcao = new Bundle();
        opcao.putString("opcao", "not1");
        Intent intent = new Intent( Noticias.this, Not.class);
        intent.putExtras(opcao);
        startActivity(intent);
    }
    public void not2(View view) {
        Bundle opcao = new Bundle();
        opcao.putString("opcao", "not2");
        Intent intent = new Intent( Noticias.this, Not.class);
        intent.putExtras(opcao);
        startActivity(intent);
    }
}