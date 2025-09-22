package com.example.pokemon.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.pokemon.PokemonDetailDialog;
import com.example.pokemon.PokemonDetailDialog;
import com.example.pokemon.R;
import com.example.pokemon.model.PokemonInfo;
import com.example.pokemon.model.PokemonType;
import com.google.android.material.chip.Chip;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<PokemonInfo> pokemonList;

    public PokemonAdapter(List<PokemonInfo> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_poke, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonInfo pokemon = pokemonList.get(position);

        holder.txtName.setText(pokemon.getName());

        List<PokemonType> types = pokemon.getTypes();
        if (types != null && !types.isEmpty()) {
            String type1 = types.get(0).getType().getName();
            holder.type1.setText(type1);
            setChipColor(holder.type1, type1);
            setBackgroundColor(holder.cardView, type1);
            holder.type1.setVisibility(View.VISIBLE);
        }

        if (types.size() > 1) {
            String type2 = types.get(1).getType().getName();
            holder.type2.setText(type2);
            setChipColor(holder.type2, type2);
            setBackgroundColor(holder.cardView, type2);
            holder.type2.setVisibility(View.VISIBLE);
        } else {
            holder.type2.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            int cardColor = holder.cardView.getCardBackgroundColor().getDefaultColor();
            PokemonDetailDialog dialog = PokemonDetailDialog.newInstance(pokemon, cardColor);
            dialog.show(((AppCompatActivity)v.getContext()).getSupportFragmentManager(), "pokemonDetail");
        });



        Glide.with(holder.itemView.getContext())
                .load(pokemon.getSprites().getFront_default())
                .into(holder.imgPokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPokemon;
        TextView txtName;
        Chip type1, type2;
        CardView cardView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPokemon = itemView.findViewById(R.id.ImgPoke);
            txtName = itemView.findViewById(R.id.nomePoke);
            type1 = itemView.findViewById(R.id.chip1);
            type2 = itemView.findViewById(R.id.chip2);
            cardView = itemView.findViewById(R.id.cardRoot);
        }
    }


    private void setChipColor(Chip chip, String typeName) {
        Context context = chip.getContext();
        int color;

        switch (typeName.toLowerCase()) {
            case "normal": color = ContextCompat.getColor(context, R.color.type_normal); break;
            case "fire": color = ContextCompat.getColor(context, R.color.type_fire); break;
            case "water": color = ContextCompat.getColor(context, R.color.type_water); break;
            case "electric": color = ContextCompat.getColor(context, R.color.type_electric); break;
            case "grass": color = ContextCompat.getColor(context, R.color.type_grass); break;
            case "ice": color = ContextCompat.getColor(context, R.color.type_ice); break;
            case "fighting": color = ContextCompat.getColor(context, R.color.type_fighting); break;
            case "poison": color = ContextCompat.getColor(context, R.color.type_poison); break;
            case "ground": color = ContextCompat.getColor(context, R.color.type_ground); break;
            case "flying": color = ContextCompat.getColor(context, R.color.type_flying); break;
            case "psychic": color = ContextCompat.getColor(context, R.color.type_psychic); break;
            case "bug": color = ContextCompat.getColor(context, R.color.type_bug); break;
            case "rock": color = ContextCompat.getColor(context, R.color.type_rock); break;
            case "ghost": color = ContextCompat.getColor(context, R.color.type_ghost); break;
            case "dragon": color = ContextCompat.getColor(context, R.color.type_dragon); break;
            case "dark": color = ContextCompat.getColor(context, R.color.type_dark); break;
            case "steel": color = ContextCompat.getColor(context, R.color.type_steel); break;
            case "fairy": color = ContextCompat.getColor(context, R.color.type_fairy); break;
            default: color = ContextCompat.getColor(context, R.color.type_default); break;
        }

        chip.setChipBackgroundColor(ColorStateList.valueOf(color));
    }


    private void setBackgroundColor(CardView cardView, String typeName) {
        Context context = cardView.getContext();
        int color;

        switch (typeName.toLowerCase()) {
            case "normal": color = ContextCompat.getColor(context, R.color.card_normal); break;
            case "fire": color = ContextCompat.getColor(context, R.color.card_fire); break;
            case "water": color = ContextCompat.getColor(context, R.color.card_water); break;
            case "electric": color = ContextCompat.getColor(context, R.color.card_electric); break;
            case "grass": color = ContextCompat.getColor(context, R.color.card_grass); break;
            case "ice": color = ContextCompat.getColor(context, R.color.card_ice); break;
            case "fighting": color = ContextCompat.getColor(context, R.color.card_fighting); break;
            case "poison": color = ContextCompat.getColor(context, R.color.card_poison); break;
            case "ground": color = ContextCompat.getColor(context, R.color.card_ground); break;
            case "flying": color = ContextCompat.getColor(context, R.color.card_flying); break;
            case "psychic": color = ContextCompat.getColor(context, R.color.card_psychic); break;
            case "bug": color = ContextCompat.getColor(context, R.color.card_bug); break;
            case "rock": color = ContextCompat.getColor(context, R.color.card_rock); break;
            case "ghost": color = ContextCompat.getColor(context, R.color.card_ghost); break;
            case "dragon": color = ContextCompat.getColor(context, R.color.card_dragon); break;
            case "dark": color = ContextCompat.getColor(context, R.color.card_dark); break;
            case "steel": color = ContextCompat.getColor(context, R.color.card_steel); break;
            case "fairy": color = ContextCompat.getColor(context, R.color.card_fairy); break;
            default: color = ContextCompat.getColor(context, R.color.card_default); break;
        }

        cardView.setCardBackgroundColor(color);
    }

}
