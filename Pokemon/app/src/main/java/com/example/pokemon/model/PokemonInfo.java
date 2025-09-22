package com.example.pokemon.model;

import java.io.Serializable;
import java.util.List;

public class PokemonInfo implements Serializable {
        private int id;
        private String name;
        private int base_experience;
        private int height;
        private int weight;
        private List<PokemonStat> stats;
        private List<PokemonType> types;
        private PokemonSprites sprites;

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public int getBase_experience() {
                return base_experience;
        }

        public int getHeight() {
                return height;
        }

        public int getWeight() {
                return weight;
        }

        public List<PokemonStat> getStats() {
                return stats;
        }

        public List<PokemonType> getTypes() {
                return types;
        }

        public PokemonSprites getSprites() {
                return sprites;
        }
}


