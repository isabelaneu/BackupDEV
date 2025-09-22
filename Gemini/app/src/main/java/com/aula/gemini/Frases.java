package com.aula.gemini;

import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Frases extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frases);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ativando API
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash", "AIzaSyDhQlmvaVvmL4fv_GsJRQndKU-O0dCOY8o");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        opcao(model);
    }

    public void opcao(GenerativeModelFutures model){
        Bundle bundle = getIntent().getExtras();
        String opcao = bundle.getString("opcao");
        TextView titulo = findViewById(R.id.titulo);


        if(opcao.equals("curiosidades")){
            titulo.setText("Frases Curiosas");

            TextInputLayout textInput = findViewById(R.id.textInput);
            TextInputEditText tema = findViewById(R.id.tema);
            Button gerarCurio = findViewById(R.id.gerarCurio);
            TextView respostaText = findViewById(R.id.respostaText);

            textInput.setVisibility(VISIBLE);
            gerarCurio.setVisibility(VISIBLE);

            gerarCurio.setOnClickListener(view -> {
                String pergunta = tema.getText().toString();

                String prompt = "Gere uma curiosidade sobre " + pergunta;
                Content content = new Content.Builder().addText(prompt).build();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        String resposta = model.generateContent(content).get().getText();

                        runOnUiThread(() -> respostaText.setText(resposta));

                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(() -> respostaText.setText("Erro ao gerar resposta."));
                    }
                });
            });

        }else{
            titulo.setText("Frases Motivacionais");
            TextView respostaText = findViewById(R.id.respostaText);
            Button gerarMot = findViewById(R.id.gerarMot);
            String prompt = "Gere uma pequena frase motivacional";
            Content content = new Content.Builder().addText(prompt).build();

            gerarMot.setVisibility(VISIBLE);

            gerarMot.setOnClickListener(view -> {
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    try {
                        String resposta = model.generateContent(content).get().getText();

                        runOnUiThread(() -> respostaText.setText(resposta));

                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(() -> respostaText.setText("Erro ao gerar resposta."));
                    }
                });
            });
        }

    }


    public void voltar(View view) {
        Intent intent = new Intent(this, menuFrases.class);
        startActivity(intent);
    }
}