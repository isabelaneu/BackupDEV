package com.aula.gamesmobile;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class AndroidPlayer extends AppCompatActivity {

    private int click = 0;
    private ImageButton[] campos = new ImageButton[9];
    private TextView vezDeJogar;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_android_player);
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

        for (int i = 0; i < campos.length; i++) {
            int index = i;
            campos[i].setOnClickListener(v -> onCampoClick(index));
        }

    }

    private void onCampoClick(int index) {
        ImageButton campo = campos[index];

        campo.setImageResource(R.drawable.x);
        campo.setClickable(false);
        vezDeJogar.setText("AndroidPlayer: ");
        click++;

        int resultadoJogo = ganhador();
        if (resultadoJogo != -1) mostrarResultado(resultadoJogo);
        else if (click < 9) new Handler(Looper.getMainLooper()).postDelayed(this::androidPlay, 1000);
    }

    private void androidPlay() {
        List<Integer> camposAndroid = new ArrayList<>();
        for (int i = 0; i < campos.length; i++) {
            if (campos[i].isClickable()) camposAndroid.add(i);
        }

        if (!camposAndroid.isEmpty()) {
            int randomIndex = new Random().nextInt(camposAndroid.size());
            int move = camposAndroid.get(randomIndex);

            campos[move].setImageResource(R.drawable.o);
            campos[move].setClickable(false);
            vezDeJogar.setText("Sua vez de jogar: ");
            click++;

            int resultadoJogo = ganhador();
            if (resultadoJogo != -1) mostrarResultado(resultadoJogo);
        }
    }

    public int ganhador() {
        Drawable.ConstantState xState = Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.x)).getConstantState();
        Drawable.ConstantState oState = Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.o)).getConstantState();

        //linhas
        for (int i = 0; i < 3; i++) {
            if (campos[i * 3].getDrawable() != null && campos[i * 3 + 1].getDrawable() != null && campos[i * 3 + 2].getDrawable() != null &&
                    campos[i * 3].getDrawable().getConstantState().equals(campos[i * 3 + 1].getDrawable().getConstantState()) &&
                    campos[i * 3].getDrawable().getConstantState().equals(campos[i * 3 + 2].getDrawable().getConstantState())) {

                if (campos[i * 3].getDrawable().getConstantState().equals(xState)) return 1;
                if (campos[i * 3].getDrawable().getConstantState().equals(oState)) return 0;
            }
        }

        // colunas
        for (int i = 0; i < 3; i++) {
            if (campos[i].getDrawable() != null && campos[i + 3].getDrawable() != null && campos[i + 6].getDrawable() != null &&
                    campos[i].getDrawable().getConstantState().equals(campos[i + 3].getDrawable().getConstantState()) &&
                    campos[i].getDrawable().getConstantState().equals(campos[i + 6].getDrawable().getConstantState())) {

                if (campos[i].getDrawable().getConstantState().equals(xState)) return 1;
                if (campos[i].getDrawable().getConstantState().equals(oState)) return 0;
            }
        }

        // diagonais
        if (campos[0].getDrawable() != null && campos[4].getDrawable() != null && campos[8].getDrawable() != null &&
                campos[0].getDrawable().getConstantState().equals(campos[4].getDrawable().getConstantState()) &&
                campos[0].getDrawable().getConstantState().equals(campos[8].getDrawable().getConstantState())) {

            if (campos[0].getDrawable().getConstantState().equals(xState)) return 1;
            if (campos[0].getDrawable().getConstantState().equals(oState)) return 0;
        }

        if (campos[2].getDrawable() != null && campos[4].getDrawable() != null && campos[6].getDrawable() != null &&
                campos[2].getDrawable().getConstantState().equals(campos[4].getDrawable().getConstantState()) &&
                campos[2].getDrawable().getConstantState().equals(campos[6].getDrawable().getConstantState())) {

            if (campos[2].getDrawable().getConstantState().equals(xState)) return 1;
            if (campos[2].getDrawable().getConstantState().equals(oState)) return 0;
        }

        boolean empate = true;
        for (ImageButton campo : campos) {
            if (campo.getDrawable() == null) {
                empate = false;
                break;
            }
        }
        if (empate) return 2;

        return -1;
    }

    private void mostrarResultado(int resultadoJogo) {
        switch (resultadoJogo) {
            case 1:
                resultado.setText("Você venceu!");
                break;
            case 0:
                resultado.setText("O Android venceu!");
                break;
            case 2:
                resultado.setText("Empate!");
                break;
        }
    }

}
