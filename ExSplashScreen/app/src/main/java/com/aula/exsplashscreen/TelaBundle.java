package com.aula.exsplashscreen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaBundle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bundle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button binfo = findViewById(R.id.button11);
        binfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.name);
                EditText phone = findViewById(R.id.telefone);
                EditText email = findViewById(R.id.email);

                Bundle envelopeDados = new Bundle();
                envelopeDados.putString("nome", name.getText().toString());
                envelopeDados.putString("telefone", phone.getText().toString());
                envelopeDados.putString("email", email.getText().toString());

                Intent intent = new Intent(TelaBundle.this, TelaInfo.class);
                intent.putExtras(envelopeDados);
                startActivity(intent);
            }
        });

        Button btDiscar = findViewById(R.id.button12);
        btDiscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText email = findViewById(R.id.telefone);

            if (email.getText().toString().isEmpty()) {
                Toast.makeText(TelaBundle.this, "Campo Obrigatório", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + email.getText().toString()));
                startActivity(intent);
            }
        }
        });


        Button btEnviar = findViewById(R.id.button13);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = findViewById(R.id.email);

                Intent enviarEmail = new Intent(Intent.ACTION_SEND);
                enviarEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                enviarEmail.putExtra(Intent.EXTRA_SUBJECT, "Suporte App");
                enviarEmail.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<html><body><h1>Corpo</h1></body></html>"));
                enviarEmail.setType("text/html");
                startActivity(enviarEmail);
            }
        });

        Button btInformacao = findViewById(R.id.button14);
        btInformacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nome = findViewById(R.id.name);
                EditText email = findViewById(R.id.email);

//                AlertDialog.Builder avisoAlert = new AlertDialog.Builder(TelaBundle.this);
//                avisoAlert.setTitle("Aviso");
//                avisoAlert.setMessage("Deseja realmente sair?")
//                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int wich) {
//                                Toast.makeText(TelaBundle.this, "Você saiu", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setNegativeButton("Não", null)
//                        .setNeutralButton("Cancelar", null);
//                avisoAlert.show();


//                Alert customizadp
                Dialog caixaAlert = new Dialog(TelaBundle.this);
                caixaAlert.setContentView(R.layout.custom);
//                Definfindo que o tamanho do layout é wrap content
                caixaAlert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                caixaAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                caixaAlert.setCancelable(false);

//                Configurar a mensagem
                TextView idTitulo = caixaAlert.findViewById(R.id.id_tituloCustom);
                TextView idMensagem = caixaAlert.findViewById(R.id.id_mensagemCustom);
                Button btSim = caixaAlert.findViewById(R.id.buttonSimCustom);
                Button btNao = caixaAlert.findViewById(R.id.buttonNaoCustom);

                idTitulo.setText("Aviso");
                idMensagem.setText("Deseja realmente sair?");

                btSim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        caixaAlert.dismiss();
                    }
                });

                caixaAlert.show();

            }
        });
    }
}