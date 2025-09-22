package com.aula.minhasreceitas.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.aula.minhasreceitas.R;
import com.aula.minhasreceitas.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle receitas = new Bundle();

        Button rec_bolo = binding.recBolo;
        rec_bolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receitas.putString("nome", "Bolo de cenoura");
                NavController navController = NavHostFragment.findNavController(DashboardFragment.this);
                navController.navigate(R.id.action_navigation_dashboard_to_receitaWebView, receitas);
            }
        });

        Button rec_frango = binding.recFrango;
        rec_frango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receitas.putString("nome", "Frango grelhado ao alho e limão");
                NavController navController = NavHostFragment.findNavController(DashboardFragment.this);
                navController.navigate(R.id.action_navigation_dashboard_to_receitaWebView, receitas);

            }
        });

        Button rec_lasanha = binding.recLasanha;
        rec_lasanha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receitas.putString("nome", "Lasanha");
                NavController navController = NavHostFragment.findNavController(DashboardFragment.this);
                navController.navigate(R.id.action_navigation_dashboard_to_receitaWebView, receitas);

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