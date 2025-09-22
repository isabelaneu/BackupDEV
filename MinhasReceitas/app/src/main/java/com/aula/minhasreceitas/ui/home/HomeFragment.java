package com.aula.minhasreceitas.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aula.minhasreceitas.R;
import com.aula.minhasreceitas.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle envelope = getArguments();
        if (envelope != null) {
            String nome = envelope.getString("nome");
            if (nome != null) {
                TextView textView = binding.nomeHome;
                textView.setText(nome);
            } else {
                binding.nomeHome.setText("Nome não fornecido");
            }
        } else {
            binding.nomeHome.setText(" ");
        }

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}