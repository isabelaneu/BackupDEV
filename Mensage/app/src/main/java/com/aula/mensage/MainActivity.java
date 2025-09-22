package com.aula.mensage;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText mobile_number;
    EditText menssage;
    Button send;
    private static final int REQUEST_CODE_SEND_SMS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão não foi concedida, solicita ao usuário
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
        }

        mobile_number = findViewById(R.id.editTextPhone);
        menssage = findViewById(R.id.menssage);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                abrir em 2 plano

                Intent telaAtual = new Intent(view.getContext().toString());
                PendingIntent pi = PendingIntent.getActivity(view.getContext(), 0, telaAtual, PendingIntent.FLAG_IMMUTABLE);

//                gerenciador de SMS

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(mobile_number.getText().toString(), null, menssage.getText().toString(), pi, null);

                Toast.makeText(getApplicationContext(), "Mensagem enviada", Toast.LENGTH_LONG).show();
            }
        });

    }
}