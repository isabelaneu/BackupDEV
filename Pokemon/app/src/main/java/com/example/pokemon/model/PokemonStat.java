
package com.example.pokemon.model;

public class PokemonStat {
    private int base_stat;
    private Stat stat;

    public int getBase_stat() { return base_stat; }
    public Stat getStat() { return stat; }

    public static class Stat {
        private String name;
        public String getName() { return name; }
    }
}


