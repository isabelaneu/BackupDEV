package com.example.pokemon;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.adapter.PokemonAdapter;
import com.example.pokemon.api.PokemonInterface;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.PokemonInfo;
import com.example.pokemon.model.PokemonResults;
import com.example.pokemon.model.PokemonStat;
import com.example.pokemon.model.PokemonType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;


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

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView2 = findViewById(R.id.recycleView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);


        getAllPokes();

        getPokeInfo("paras");
    }

    private void getAllPokes() {
        PokemonInterface pokemonInterface = retrofit.create(PokemonInterface.class);
        Call<PokemonResults> call = pokemonInterface.getAllPokes();

        call.enqueue(new Callback<PokemonResults>() {
            @Override
            public void onResponse(Call<PokemonResults> call, Response<PokemonResults> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemons = response.body().getResults();

                    // lista final com os detalhes completos
                    List<PokemonInfo> pokemonInfoList = new ArrayList<>();

                    for (Pokemon p : pokemons) {
                        // para cada pokemon da lista inicial, buscar os detalhes
                        Call<PokemonInfo> infoCall = pokemonInterface.getPokemonInfo(p.getNome());
                        infoCall.enqueue(new Callback<PokemonInfo>() {
                            @Override
                            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    PokemonInfo detail = response.body();
                                    pokemonInfoList.add(detail);

                                    // atualizar adapter sempre que chegar um novo
                                    recyclerView.setAdapter(new PokemonAdapter(pokemonInfoList));
                                    recyclerView2.setAdapter(new PokemonAdapter(pokemonInfoList));


                                }
                            }

                            @Override
                            public void onFailure(Call<PokemonInfo> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void getPokeInfo(String nome){
        PokemonInterface pokemonInterface = retrofit.create(PokemonInterface.class);
        Call<PokemonInfo> call = pokemonInterface.getPokemonInfo(nome);

        call.enqueue(new Callback<PokemonInfo>(){
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonInfo detail = response.body();
                }
            }

            @Override
            public void onFailure(Call<PokemonInfo> call, Throwable t){}

        });
    }
}