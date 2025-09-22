package com.example.restapi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restapi.adapter.PhotoAdapter;
import com.example.restapi.api.Photos;
import com.example.restapi.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gallery extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Chamar a API de photo
        getAllPhotos();
    }

    private void getAllPhotos() {
//        definir a URL da API
        String url = "https://picsum.photos";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        criar a chamada do endpoint
        Photos photos = retrofit.create(Photos.class);
        Call<List<Photo>> call = photos.getAllPhotos();

        Call<List<Photo>> callTwo = photos.getPhoto(2);

//        executar a chamada
        call.enqueue(new Callback<List<Photo>>(){
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photoList = response.body();

//                preencher o recycle view
                recyclerView.setAdapter(new PhotoAdapter(photoList));
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

        callTwo.enqueue(new Callback<List<Photo>>(){
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photoList = response.body();
                recyclerView.setAdapter(new PhotoAdapter(photoList));
            }
            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {}
        });
    }
}