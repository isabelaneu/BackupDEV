package com.aula.gamesmobile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IA extends AppCompatActivity {

    private ImageButton[] campos = new ImageButton[9];
    private TextView vezDeJogar;
    private TextView resultado;
    private int click = 0;
    private Drawable xDrawable;
    private Drawable oDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vezDeJogar = findViewById(R.id.vezDeJogar2);
        resultado = findViewById(R.id.resultado2);

        campos[0] = findViewById(R.id.campo11);
        campos[1] = findViewById(R.id.campo22);
        campos[2] = findViewById(R.id.campo33);
        campos[3] = findViewById(R.id.campo44);
        campos[4] = findViewById(R.id.campo55);
        campos[5] = findViewById(R.id.campo66);
        campos[6] = findViewById(R.id.campo77);
        campos[7] = findViewById(R.id.campo88);
        campos[8] = findViewById(R.id.campo99);

        xDrawable = ContextCompat.getDrawable(this, R.drawable.x);
        oDrawable = ContextCompat.getDrawable(this, R.drawable.o);

        for (int i = 0; i < campos.length; i++) {
            int index = i;
            campos[i].setOnClickListener(v -> onCampoClick(index));
        }
    }

    private String gerarPromptDoTabuleiro() {
        StringBuilder prompt = new StringBuilder("#IDENTIDADE#\n" +
                "\n" +
                "Voce é um jogador \"O\" no jogo da velha.\n" +
                "\n" +
                "#OBJETIVO#\n" +
                "\n" +
                "Voce deverá analisar as jogadas do jogador \"O\" com o objetivo de derrotar o jogador \"X\".\n" +
                "\n" +
                "#CONTEXTO#\n" +
                "\n" +
                "O jogo da velha é formado pela representação dos números de 1 até 9 que indica a posição no tabuleiro. Calcule cada jogada para o jogador \"O\" com o objetivo de vencer a partida.\n" +
                "\n" +
                "A resposta deve ser formatada pelo numero que representa a jogada do jogador \"O\". Não liste as posições.\n" +
                "\n"
        );

        for (int i = 0; i < campos.length; i++) {
            String valor = " ";
            Drawable drawable = campos[i].getDrawable();
            if (drawable != null) {
                if (drawable.getConstantState() != null) {
                    if (drawable.getConstantState().equals(xDrawable.getConstantState())) {
                        valor = "X";
                    } else if (drawable.getConstantState().equals(oDrawable.getConstantState())) {
                        valor = "O";
                    }
                }
            }
            prompt.append((i + 1)).append(":").append(valor);
            if (i < campos.length - 1) prompt.append(", ");
        }

        prompt.append("\n\"#AÇÃO#\\n\"Realize a jogada do jogador \\\"O\\\" para  vencer a partida.Qual a proxima jogada para o \\\"O\\\"? \"");
        Log.d("Prompt", prompt.toString());
        return prompt.toString();
    }

    private void IAPlay() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                String prompt = gerarPromptDoTabuleiro();

                Content.Builder contentBuilder = new Content.Builder();
                contentBuilder.addText(prompt);
                Content content = contentBuilder.build();

                GenerativeModel gm = new GenerativeModel("gemini-1.5-flash","AIzaSyDhQlmvaVvmL4fv_GsJRQndKU-O0dCOY8o");
                GenerativeModelFutures model = GenerativeModelFutures.from(gm);

                String resposta = model.generateContent(content).get().getText();

                runOnUiThread(() -> {
                    try {
                        int posicao = Integer.parseInt(resposta.replaceAll("[^0-9]", "")) - 1;
                        if (posicao >= 0 && posicao < campos.length && campos[posicao].isClickable()) {
                            campos[posicao].setImageResource(R.drawable.o);
                            campos[posicao].setClickable(false);
                            vezDeJogar.setText("Sua vez de jogar:");
                            click++;

                            Log.d("Resposta", resposta);
                            int resultadoJogo = ganhador();
                            if (resultadoJogo != -1) mostrarResultado(resultadoJogo);
                        }
                    } catch (NumberFormatException e) {
                        resultado.setText("Erro: Resposta da IA inválida - " + resposta);
                        e.printStackTrace();
                    } catch (Exception e) {
                        resultado.setText("Erro na resposta da IA: " + resposta);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> resultado.setText("Erro ao acessar IA."));
            }
        });
    }

    private void onCampoClick(int index) {
        ImageButton campo = campos[index];
        campo.setImageResource(R.drawable.x);
        campo.setClickable(false);
        vezDeJogar.setText("IA");
        click++;

        int resultadoJogo = ganhador();
        if (resultadoJogo != -1) mostrarResultado(resultadoJogo);
        else if (click < 9) {
            new Handler(Looper.getMainLooper()).postDelayed(this::IAPlay, 100);
        }
    }

    public int ganhador() {
        if (click < 5) return -1;

        Drawable.ConstantState xState = xDrawable.getConstantState();
        Drawable.ConstantState oState = oDrawable.getConstantState();

        int[][] linhasDeVitoria = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Linhas
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colunas
                {0, 4, 8}, {2, 4, 6}             // Diagonais
        };

        for (int[] linha : linhasDeVitoria) {
            Drawable drawable1 = campos[linha[0]].getDrawable();
            Drawable drawable2 = campos[linha[1]].getDrawable();
            Drawable drawable3 = campos[linha[2]].getDrawable();

            if (drawable1 != null && drawable2 != null && drawable3 != null &&
                    drawable1.getConstantState() != null &&
                    drawable1.getConstantState().equals(drawable2.getConstantState()) &&
                    drawable1.getConstantState().equals(drawable3.getConstantState())) {
                if (drawable1.getConstantState().equals(xState)) return 1;
                if (drawable1.getConstantState().equals(oState)) return 0;
            }
        }

        return click == 9 ? 2 : -1;
    }

    private void mostrarResultado(int resultadoJogo) {
        switch (resultadoJogo) {
            case 1:
                resultado.setText("Você venceu!");
                break;
            case 0:
                resultado.setText("A IA venceu!");
                break;
            case 2:
                resultado.setText("Empate!");
                break;
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}