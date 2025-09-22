package com.aula.authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView nome;
    TextView email;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FirebaseUser user = mAuth.getCurrentUser();
        nome = findViewById(R.id.textnome);
        email = findViewById(R.id.textemail);

        ((TextView) findViewById(R.id.textnome)).setText(user.getDisplayName());
            ((TextView) findViewById(R.id.textemail)).setText(user.getEmail());
            Glide.with(MainActivity.this)
                    .load(user.getPhotoUrl())
                    .into((android.widget.ImageView) findViewById(R.id.imageButton));
        ((Button) findViewById(R.id.button3)).setOnClickListener(v -> {
            mAuth.signOut();
            mGoogleSignInClient.signOut();
            finish();
        });

    }
}