package com.aula.pratica2mobile.ui.notifications;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.aula.pratica2mobile.R;
import com.aula.pratica2mobile.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private int contador = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton menos = binding.botaoMenos;
        ImageButton mais = binding.botaoMais;
        TextView numero = binding.textView15;
        TextView status = binding.textView16;


        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                numero.setText(String.valueOf(contador));

            }

        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contador <= 0){
                    contador = 0;
                }
                else {
                    contador--;
                    numero.setText(String.valueOf(contador));
                }
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle copos = new Bundle();
                copos.putInt("copos", contador);

                NavController navController = NavHostFragment.findNavController(NotificationsFragment.this);
                navController.navigate(R.id.navigation_perfil, copos);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}