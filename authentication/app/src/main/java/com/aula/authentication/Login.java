package com.aula.authentication;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.credentials.GetCredentialRequest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Button button;
    EditText email;
    EditText senha;
    TextView cadastro;
    SignInButton google;
    GoogleSignInClient mGoogleSignInClient;
    ActivityResultLauncher<Intent> mGoogleSignInLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    GoogleSignInAccount account = task.getResult();
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(Login.this, task1 -> {
                            if (task1.isSuccessful()) {
//                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                                ((TextView) findViewById(R.id.textnome)).setText(user.getDisplayName());
//                                ((TextView) findViewById(R.id.textemail)).setText(user.getEmail());
//                                Glide.with(Login.this)
//                                        .load(user.getPhotoUrl())
//                                        .into((ShapeableImageView) findViewById(R.id.imageButton));
                                startActivity(new Intent(Login.this, MainActivity.class));

                            } else {
                                Toast.makeText(Login.this, "Erro ao fazer login com Google", Toast.LENGTH_SHORT).show();                            }
                        });
                }
            }
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Botão login com email
        button = findViewById(R.id.button2);
        email = findViewById(R.id.emaillogin);
        senha = findViewById(R.id.senhalogin);
        cadastro = findViewById(R.id.cadastrese);
        google = findViewById(R.id.btnGoogle);

        button.setOnClickListener(v -> loginComEmail());
        google.setOnClickListener(v -> loginComGoogle());
        cadastro.setOnClickListener(v -> startActivity(new Intent(Login.this, Cadastro.class)));
        findViewById(R.id.forgotpass).setOnClickListener(v -> showResetPasswordDialog());


        if (mAuth.getCurrentUser() != null) {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            ((TextView) findViewById(R.id.textnome)).setText(user.getDisplayName());
//            ((TextView) findViewById(R.id.textemail)).setText(user.getEmail());
//            Glide.with(Login.this)
//                    .load(user.getPhotoUrl())
//                    .into((android.widget.ImageView) findViewById(R.id.imageButton));
            startActivity(new Intent(Login.this, MainActivity.class));

        }

    }

    private void showResetPasswordDialog() {
//        criar dialog para solicitar email e resetar a senha

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Redefinir Senha");
        builder.setMessage("Digite seu email para receber o link de redefinição de senha");
        EditText emailEditText = new EditText(this);
        builder.setView(emailEditText);

        builder.setPositiveButton("Redefinir", (dialog, which) -> {
            String email = emailEditText.getText().toString();
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Email de redefinição de senha enviado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Erro ao enviar email de redefinição de senha", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        });
        builder.show();
    }

    private void loginComEmail() {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
        .addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(Login.this, MainActivity.class));
            } else {
                String excecao;
                try {
                    throw task.getException();
                } catch (FirebaseAuthInvalidUserException e) {
                    excecao = "Usuário não está cadastrado";
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    excecao = "Email e senha inválidos";
                } catch (Exception e) {
                    excecao = "Erro interno";
                }
                Toast.makeText(Login.this, excecao, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginComGoogle() {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("609886854143-02mpiurm42fk5122uh8nlgbcqffspktf.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, options);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mGoogleSignInLauncher.launch(signInIntent);
    }
}