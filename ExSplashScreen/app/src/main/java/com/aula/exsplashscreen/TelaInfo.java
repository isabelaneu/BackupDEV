package com.aula.exsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        info();
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, TelaBundle.class);
        startActivity(intent);
    }

    public void info(){
        Bundle bundle = getIntent().getExtras();
        String nome = bundle.getString("nome");
        String telefone = bundle.getString("telefone");
        String email = bundle.getString("email");

        EditText resultado = findViewById(R.id.editTextText);
        resultado.setText("Nome: " +  nome + "\nTelefone" + telefone + "\nEmail" + email);
    }
}