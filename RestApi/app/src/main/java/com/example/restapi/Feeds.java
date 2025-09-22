package com.example.restapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.restapi.api.FeedApi;
import com.example.restapi.model.Feed;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Feeds extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ((Button) findViewById(R.id.btPost)).setOnClickListener(view -> {
            chamarApi_PostFeed();
        });
    }

    private void chamarApi_PostFeed() {
        // Configurar o retrofit

        String url = "https://jsonplaceholder.typicode.com";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Enviando o objeto


        Feed feeds = new Feed(
                ((TextInputEditText) findViewById(R.id.postId)).toString(),
                ((TextInputEditText) findViewById(R.id.postUserId)).toString(),
                ((TextInputEditText) findViewById(R.id.postTitle)).toString(),
                ((TextInputEditText) findViewById(R.id.postBody)).toString()
        );

        FeedApi feedApi = retrofit.create(FeedApi.class);
        Call<Feed> feedsCall = feedApi.salvarFeed(feeds);

        feedsCall.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    ((EditText) findViewById(R.id.txtResultado)).setVisibility(View.VISIBLE);
                    ((EditText) findViewById(R.id.txtResultado)).setText(
                            "Status" + response.code() + "\n" + "Body: " + response.body().toString());

                    Feed body = response.body();
                }

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            };

        });

    };
}