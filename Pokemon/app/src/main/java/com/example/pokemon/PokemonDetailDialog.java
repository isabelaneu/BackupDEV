package com.example.pokemon;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.pokemon.model.PokemonInfo;
import com.example.pokemon.model.PokemonStat;
import com.example.pokemon.model.PokemonType;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.List;

public class PokemonDetailDialog extends BottomSheetDialogFragment {

    private static final String ARG_POKEMON = "arg_pokemon";
    private static final String ARG_COLOR = "arg_color";

    private PokemonInfo pokemon;
    private int bgColor;

    public static PokemonDetailDialog newInstance(PokemonInfo pokemon, int color) {
        PokemonDetailDialog dialog = new PokemonDetailDialog();
        Bundle args = new Bundle();
        args.putSerializable(ARG_POKEMON, pokemon);
        args.putInt(ARG_COLOR, color);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_pokemon_detail_dialog, container, false);

        if (getArguments() != null) {
            pokemon = (PokemonInfo) getArguments().getSerializable(ARG_POKEMON);
            bgColor = getArguments().getInt(ARG_COLOR);
        }

        view.setBackgroundColor(bgColor);

        ImageView img = view.findViewById(R.id.imageView);
        TextView name = view.findViewById(R.id.textViewNome);
        Chip chip1 = view.findViewById(R.id.chip);
        Chip chip2 = view.findViewById(R.id.chip3);

        name.setText(pokemon.getName());

        Glide.with(this)
                .load(pokemon.getSprites().getFront_default())
                .into(img);

        List<PokemonType> types = pokemon.getTypes();
        if (types.size() > 0) {
            chip1.setText(types.get(0).getType().getName());
            setChipColor(chip1, types.get(0).getType().getName());
        } else {
            chip1.setVisibility(View.GONE);
        }

        if (types.size() > 1) {
            chip2.setText(types.get(1).getType().getName());
            setChipColor(chip2, types.get(1).getType().getName());
        } else {
            chip2.setVisibility(View.GONE);
        }

        LinearLayout statsLayout = view.findViewById(R.id.statsLayout);

        for (PokemonStat stat : pokemon.getStats()) {
            LinearLayout statRow = new LinearLayout(getContext());
            statRow.setOrientation(LinearLayout.HORIZONTAL);
            statRow.setPadding(0, 8, 0, 8);

            TextView statName = new TextView(getContext());
            statName.setText(stat.getStat().getName() + ": ");
            statName.setTextColor(Color.BLACK);
            statName.setTextSize(16);
            statName.setTypeface(statName.getTypeface(), android.graphics.Typeface.BOLD);

            TextView statValue = new TextView(getContext());
            statValue.setText(String.valueOf(stat.getBase_stat()));
            statValue.setTextColor(Color.BLACK);
            statValue.setTextSize(16);

            statRow.addView(statName);
            statRow.addView(statValue);

            statsLayout.addView(statRow);

            View divider = new View(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 2);
            params.setMargins(0, 4, 0, 4);
            divider.setLayoutParams(params);
            divider.setBackgroundColor(Color.LTGRAY);

            statsLayout.addView(divider);
        }


        return view;
    }

    private void setChipColor(Chip chip, String typeName) {
        Context context = chip.getContext();
        int bgColor;

        switch (typeName.toLowerCase()) {
            case "fire": bgColor = ContextCompat.getColor(context, R.color.type_fire); break;
            case "water": bgColor = ContextCompat.getColor(context, R.color.type_water); break;
            case "grass": bgColor = ContextCompat.getColor(context, R.color.type_grass); break;
            case "electric": bgColor = ContextCompat.getColor(context, R.color.type_electric); break;
            case "poison": bgColor = ContextCompat.getColor(context, R.color.type_poison); break;
            case "ground": bgColor = ContextCompat.getColor(context, R.color.type_ground); break;
            case "flying": bgColor = ContextCompat.getColor(context, R.color.type_flying); break;
            case "psychic": bgColor = ContextCompat.getColor(context, R.color.type_psychic); break;
            case "bug": bgColor = ContextCompat.getColor(context, R.color.type_bug); break;
            case "rock": bgColor = ContextCompat.getColor(context, R.color.type_rock); break;
            case "ghost": bgColor = ContextCompat.getColor(context, R.color.type_ghost); break;
            case "dragon": bgColor = ContextCompat.getColor(context, R.color.type_dragon); break;
            case "dark": bgColor = ContextCompat.getColor(context, R.color.type_dark); break;
            case "steel": bgColor = ContextCompat.getColor(context, R.color.type_steel); break;
            case "fairy": bgColor = ContextCompat.getColor(context, R.color.type_fairy); break;
            case "ice": bgColor = ContextCompat.getColor(context, R.color.type_ice); break;
            case "fighting": bgColor = ContextCompat.getColor(context, R.color.type_fighting); break;
            case "normal": bgColor = ContextCompat.getColor(context, R.color.type_normal); break;
            default: bgColor = ContextCompat.getColor(context, R.color.type_default); break;
        }

        chip.setChipBackgroundColor(ColorStateList.valueOf(bgColor));

        int textColor = isColorDark(bgColor) ? Color.WHITE : Color.BLACK;
        chip.setTextColor(textColor);
    }

    private boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) +
                0.587 * Color.green(color) +
                0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}
