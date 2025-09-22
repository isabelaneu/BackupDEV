package com.aula.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aula.myapplication.databinding.ActivityScreenJavaViewBingBinding;
import com.bumptech.glide.Glide;

public class ScreenJavaViewBing extends AppCompatActivity {

//    EditText entrada;
//    Button button;
//    TextView saida;

    private ActivityScreenJavaViewBingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_screen_java_view_bing);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        JEITO DIFÍCIL DE FAZER
//        entrada = findViewById(R.id.entrada);
//        button = findViewById(R.id.button7);
//        saida = findViewById(R.id.saida);
//
//        button.setOnClickListener(view -> {
//            saida.setText(entrada.getText());
//        });

//        JEITO FÁCIL DE FAZER (VIEW BINDING)
        binding = ActivityScreenJavaViewBingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//        binding.button7.setOnClickListener(view -> {
//            binding.saida.setText(binding.entrada.getText());
//        });


//        Adicionando um gif na tela
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.gifsplash).into(imageView);


//        Adicionando splash screen com um delay de 4 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTela();
            }
        }, 4000);


    }

    void abrirTela(){
        Intent rota = new Intent(this, main.class);
        startActivity(rota);
        finish();
    }
}