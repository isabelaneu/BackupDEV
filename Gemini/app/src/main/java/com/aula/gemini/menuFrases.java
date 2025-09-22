package com.aula.gemini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class menuFrases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_frases);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void curiosidades(View view) {
        Bundle opcao = new Bundle();
        opcao.putString("opcao", "curiosidades");
        Intent intent = new Intent( menuFrases.this, Frases.class);
        intent.putExtras(opcao);
        startActivity(intent);
    }

    public void motivacao(View view) {
        Bundle opcao = new Bundle();
        opcao.putString("opcao", "motivacao");
        Intent intent = new Intent( menuFrases.this, Frases.class);
        intent.putExtras(opcao);
        startActivity(intent);
    }
}