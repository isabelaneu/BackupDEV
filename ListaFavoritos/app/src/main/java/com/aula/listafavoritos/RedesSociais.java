package com.aula.listafavoritos;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RedesSociais extends AppCompatActivity {

    WebView web;
    ProgressBar pb;
    ImageButton face;
    ImageButton insta;
    ImageButton youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_redes_sociais);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        web = findViewById(R.id.web);
        pb = findViewById(R.id.progressBar);
        face = findViewById(R.id.face);
        insta = findViewById(R.id.insta);
        youtube = findViewById(R.id.youtube);

        web.getSettings().setJavaScriptEnabled(true);

        insta.setOnClickListener(v -> {
            web.loadUrl("https://www.instagram.com/");
        });

        face.setOnClickListener(v -> {
            web.loadUrl("https://www.facebook.com/");
        });

        youtube.setOnClickListener(v -> {
            web.loadUrl("https://www.youtube.com/");
        });

//        permitir abrir novas paginas no webView
        web.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()){
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}