package com.example.pokemon.model;

public class PokemonType {

    private int slot;
    private Type type;

    public int getSlot() {
        return slot;
    }

    public Type getType() {
        return type;
    }

    public static class Type{
        private String name;
        public String getName() { return name; }
    }
}
