package com.aula.exsplashscreen;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aula.exsplashscreen.databinding.ActivityEx1Binding;

public class Ex1 extends AppCompatActivity {

    private ActivityEx1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ex1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityEx1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void calcular(View view) {
        EditText edtAlcool = findViewById(R.id.editTextNumberDecimal);
        EditText edtGasolina = findViewById(R.id.editTextNumberDecimal2);

        double alcool = Double.parseDouble(edtAlcool.getText().toString());
        double gasolina = Double.parseDouble(edtGasolina.getText().toString());
        double resultado = alcool / gasolina;

        if(resultado < 0.7){
            binding.textView3.setText("Melhor abastecer com Álcool");
        }else{
            binding.textView3.setText("Melhor abastecer com Gasolina");
        }

    }
}