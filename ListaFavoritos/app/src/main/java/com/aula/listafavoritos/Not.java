package com.aula.listafavoritos;

import static android.view.View.VISIBLE;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Not extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_not);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        web = findViewById(R.id.web);

    }
    public void opcao() {
        Bundle bundle = getIntent().getExtras();
        String opcao = bundle.getString("opcao");

        web.getSettings().setJavaScriptEnabled(true);

        if (opcao.equals("not1")) {
            web.loadUrl("https://g1.globo.com/mundo/noticia/2025/04/28/putin-declara-cessar-fogo-de-3-dias-na-ucrania.ghtml");
        } else if (opcao.equals("not2")) {
            web.loadUrl("https://g1.globo.com/educacao/noticia/2025/04/28/enem-2025-novo-prazo-para-pedido-de-isencao-da-taxa-de-inscricao.ghtml");
        }

//        permitir abrir novas paginas no webView
        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK && web.canGoBack()){
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}