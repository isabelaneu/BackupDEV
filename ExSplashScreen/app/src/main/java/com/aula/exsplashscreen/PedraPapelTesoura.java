package com.aula.exsplashscreen;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aula.exsplashscreen.databinding.ActivityPedraPapelTesouraBinding;

public class PedraPapelTesoura extends AppCompatActivity {

    private ActivityPedraPapelTesouraBinding binding;
    private int escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedra_papel_tesoura);

        binding = ActivityPedraPapelTesouraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.imageView9.setOnClickListener(view -> {
            escolha = 0;  // Pedra
//            GradientDrawable drawable = (GradientDrawable) binding.imageView9.getBackground();
//            drawable.setStroke(8, getResources().getColor(R.color.black, getTheme()));
            jogar();
        });

        binding.imageView10.setOnClickListener(view -> {
            escolha = 1;  // Papel
//            GradientDrawable drawable = (GradientDrawable) binding.imageView10.getBackground();
//            drawable.setStroke(8, getResources().getColor(R.color.black, getTheme()));
            jogar();
        });

        binding.imageView11.setOnClickListener(view -> {
            escolha = 2;  // Tesoura
//            GradientDrawable drawable = (GradientDrawable) binding.imageView11.getBackground();
//            drawable.setStroke(8, getResources().getColor(R.color.black, getTheme()));
            jogar();
        });
    }

    public void jogar() {


        int jogadaComputador = (int) (Math.random() * 3);


        if (jogadaComputador == 0) {
            binding.imageView8.setImageResource(R.drawable.pedra);
        } else if (jogadaComputador == 1) {
            binding.imageView8.setImageResource(R.drawable.papel);
        } else {
            binding.imageView8.setImageResource(R.drawable.tesoura);
        }


        if ((escolha - jogadaComputador) % 3 == 0) {
            binding.textView18.setText("Empate!");
        } else if ((escolha - jogadaComputador) % 3 == 1) {
            binding.textView18.setText("Você ganhou!");
        } else {
            binding.textView18.setText("Você perdeu!");
        }
    }
}
