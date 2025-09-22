package com.example.pokemon.api;

import com.example.pokemon.model.PokemonInfo;
import com.example.pokemon.model.PokemonResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonInterface {
    @GET("pokemon?limit=50")
    Call<PokemonResults> getAllPokes();

    @GET("pokemon/{name}")
    Call<PokemonInfo> getPokemonInfo(@Path("name") String name);
}
