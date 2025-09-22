package com.aula.minhasreceitas.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.aula.minhasreceitas.R;
import com.aula.minhasreceitas.databinding.FragmentNotificationsBinding;
import com.aula.minhasreceitas.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    TextInputEditText nome;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button = binding.button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = binding.nomePerfil.getText().toString();
                Bundle envelopeDados = new Bundle();
                envelopeDados.putString("nome", nome);

                NavController navController = NavHostFragment.findNavController(NotificationsFragment.this);
                navController.navigate(R.id.navigation_home, envelopeDados);

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