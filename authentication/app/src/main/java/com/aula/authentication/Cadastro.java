
package com.aula.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Cadastro extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView entrar;
    EditText nome;
    EditText email;
    EditText senha;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        entrar = findViewById(R.id.entrar);
        nome = findViewById(R.id.nomecadas);
        email = findViewById(R.id.emailcadas);
        senha = findViewById(R.id.senhacadas);
        cadastrar = findViewById(R.id.button);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeStr = nome.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String senhaStr = senha.getText().toString().trim();

                if (nomeStr.isEmpty() || emailStr.isEmpty() || senhaStr.isEmpty()) {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailStr, senhaStr)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nomeStr)
                                        .build();

                                user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(Cadastro.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Cadastro.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        //Toast.makeText(Cadastro.this, "Erro ao salvar nome do usuário", Toast.LENGTH_SHORT).show();
                                        System.out.println(task1.getException().getMessage());
                                    }
                                });
                            }


                        } else {
                            String excecao = "";
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthUserCollisionException e){
                                excecao = "Já existe um usuário cadastrado com esse email";
                            }catch (FirebaseAuthWeakPasswordException e){
                                excecao = "A senha deve conter no mínimo 8 caracteres";
                            }catch (Exception e){
                                excecao = "Erro interno";
                            }
                            Toast.makeText(Cadastro.this, excecao, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cadastro.this, Login.class);
                startActivity(intent);
            }
        });
    }
}