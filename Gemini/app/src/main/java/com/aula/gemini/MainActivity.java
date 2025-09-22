package com.aula.gemini;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private StringBuilder historicoMensagens = new StringBuilder();
    private TextView saida;
    private  TextInputEditText texto;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putString("saida", saida.getText().toString());
        outState.putString("pergunta", texto.getText().toString());
        super.onSaveInstanceState(outState, outPersistentState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            saida.setText(savedInstanceState.getString("saida"));
            texto.setText(savedInstanceState.getString("texto"));
        }
    }



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

        ImageButton button = findViewById(R.id.imageButton);
        TextInputEditText texto = findViewById(R.id.msg);
        TextView saida = findViewById(R.id.textView);

        // Ativando API
        GenerativeModel gm = new GenerativeModel("gemini-1.5-flash", "AIzaSyDhQlmvaVvmL4fv_GsJRQndKU-O0dCOY8o");
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Executor executor = Executors.newSingleThreadExecutor();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagemUsuario = texto.getText().toString();

                // Adicionar mensagem do usuário ao histórico
                historicoMensagens.append("Usuário: ").append(mensagemUsuario).append("\n");

                // Criar conteúdo com o histórico completo
                Content content = new Content.Builder()
                        .addText(historicoMensagens.toString())
                        .build();

                // Enviar para a API (Gemini)
                ListenableFuture<GenerateContentResponse> resposta = model.generateContent(content);
                Futures.addCallback(resposta, new FutureCallback<GenerateContentResponse>() {
                    @Override
                    public void onSuccess(GenerateContentResponse result) {
                        String respostaAPI = result.getText();

                        // Adicionar resposta da API ao histórico
                        historicoMensagens.append("Gemini: ").append(respostaAPI).append("\n");

                        // Mostrar o histórico completo na tela
                        saida.setText(historicoMensagens.toString());

                        // Limpar a caixa de texto
                        texto.setText("");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "Erro na requisição", Toast.LENGTH_SHORT).show();
                        });
                        System.out.println(t.getMessage());
                    }
                }, executor);
            }
        });
    }

    public void menuFrases(View view) {
        Intent intent = new Intent(this, menuFrases.class);
        startActivity(intent);
    }
}
