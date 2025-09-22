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
import com.aula.exsplashscreen.databinding.ActivityEx2Binding;

public class Ex2 extends AppCompatActivity {

    private ActivityEx2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ex2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityEx2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void imc(View view) {

        double peso = Double.parseDouble(binding.editTextNumberDecimal3.getText().toString());
        double altura = Double.parseDouble(binding.editTextNumberDecimal4.getText().toString());
        double resultado = peso / (altura*altura);
        System.out.println(resultado);

        if(resultado < 16){
            binding.resultado.setText("Magreza grave");
        } else if (resultado >=16 && resultado<17) {
            binding.resultado.setText("Magreza moderada");
        } else if (resultado >=17 && resultado<18.5) {
            binding.resultado.setText("Magreza leve");
        } else if (resultado >=18.5 && resultado<25) {
            binding.resultado.setText("Saudável");
        }else if (resultado >=25 && resultado<30) {
            binding.resultado.setText("Sobrepeso");
        }else if (resultado >=30 && resultado<35) {
            binding.resultado.setText("Obesidade Grau I");
        }else if (resultado >=35 && resultado<40) {
            binding.resultado.setText("Obesidade Grau II");
        }else if (resultado > 40) {
            binding.resultado.setText("Obesidade Grau III");
        }
    }
}