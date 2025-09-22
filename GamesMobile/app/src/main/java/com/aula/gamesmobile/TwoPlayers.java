package com.aula.gamesmobile;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TwoPlayers extends AppCompatActivity {
    private int click = 0;
    private ImageButton[] campos = new ImageButton[9];
    private String jogA = "";
    private String jogB = "";
    private TextView vezDeJogar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two_players);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        vezDeJogar = findViewById(R.id.vezDeJogar2);
        resultado = findViewById(R.id.resultado2);

        // Inicializa os botões do jogo
        campos[0] = findViewById(R.id.campo11);
        campos[1] = findViewById(R.id.campo22);
        campos[2] = findViewById(R.id.campo33);
        campos[3] = findViewById(R.id.campo44);
        campos[4] = findViewById(R.id.campo55);
        campos[5] = findViewById(R.id.campo66);
        campos[6] = findViewById(R.id.campo77);
        campos[7] = findViewById(R.id.campo88);
        campos[8] = findViewById(R.id.campo99);

        for (int i = 0; i < campos.length; i++) {
            int index = i;
            campos[i].setOnClickListener(v -> onCampoClick(index));
        }

        Dialog insert = new Dialog(TwoPlayers.this);
        insert.setContentView(R.layout.custom);
        insert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        insert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        insert.setCancelable(false);

        EditText editJogA = insert.findViewById(R.id.editTextText);
        EditText editJogB = insert.findViewById(R.id.editTextText2);
        Button confirmar = insert.findViewById(R.id.button);

        confirmar.setOnClickListener(view -> {
            jogA = editJogA.getText().toString();
            jogB = editJogB.getText().toString();
            vezDeJogar.setText("Vez de jogar: " + jogA);
            insert.dismiss();
        });

        insert.show();
    }

    private void onCampoClick(int index) {
        ImageButton campo = campos[index];

        if (click % 2 == 0) {
            campo.setImageResource(R.drawable.x);
            campo.setClickable(false);
            vezDeJogar.setText("Vez de jogar: " + jogB);
            ganhador();
        } else {
            campo.setImageResource(R.drawable.o);
            campo.setClickable(false);
            vezDeJogar.setText("Vez de jogar: " + jogA);
            ganhador();
        }
        click++;
    }

    private void ganhador() {
        Drawable xDrawable = ContextCompat.getDrawable(this, R.drawable.x);
        Drawable oDrawable = ContextCompat.getDrawable(this, R.drawable.o);

        int[][] linhas = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] linha : linhas) {
            if (campos[linha[0]].getBackground().getConstantState().equals(xDrawable) &&
                    campos[linha[1]].getBackground().getConstantState().equals(xDrawable.getConstantState()) &&
                    campos[linha[2]].getBackground().getConstantState().equals(xDrawable.getConstantState())) {
                vezDeJogar.setText("Jogador"+ jogA +" venceu");
                return;
            }
        }

        for (int[] linha : linhas) {
            if (campos[linha[0]].getBackground().getConstantState().equals(oDrawable) &&
                    campos[linha[1]].getBackground().getConstantState().equals(oDrawable.getConstantState()) &&
                    campos[linha[2]].getBackground().getConstantState().equals(oDrawable.getConstantState())) {
                vezDeJogar.setText("Jogador"+ jogB +" venceu");
                return;
            }
        }
    }


}
