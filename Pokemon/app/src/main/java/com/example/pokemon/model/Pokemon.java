package com.example.pokemon.model;

public class Pokemon {
    private String name;
    private String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getNome() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

