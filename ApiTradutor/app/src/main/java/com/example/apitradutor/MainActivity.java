package com.example.apitradutor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class MainActivity extends AppCompatActivity {
    Button botao;
    TextInputEditText input;
    TextView text;
    TextView originalText;

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

        botao = findViewById(R.id.button);
        input = findViewById(R.id.input);
        text = findViewById(R.id.texto);

        botao.setOnClickListener( V -> traduzir());
    }

    private void traduzir() {
        new Thread(() -> {
            try {
                Translate translate = TranslateOptions.newBuilder()
                        .setApiKey("AIzaSyBXgfWM19zLZbAI91EPxKZ8fQzPGmbxHEc")
                        .build()
                        .getService();

                Translation translation = translate.translate(
                        input.getText().toString(),
                        Translate.TranslateOption.sourceLanguage("pt"),
                        Translate.TranslateOption.targetLanguage("en")
                );

                runOnUiThread(() -> text.setText(translation.getTranslatedText()));

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> text.setText("Erro: " + e.getMessage()));
            }
        }).start();
    }

}