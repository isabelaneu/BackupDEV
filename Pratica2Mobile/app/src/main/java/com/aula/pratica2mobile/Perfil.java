package com.aula.pratica2mobile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Perfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Perfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Perfil.
     */
    // TODO: Rename and change types and number of parameters
    public static Perfil newInstance(String param1, String param2) {
        Perfil fragment = new Perfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int qnt_copos = bundle.getInt("copos", 0);

            ImageButton status = view.findViewById(R.id.status);

            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qnt_copos = 0;
                    if (getArguments() != null) {
                        qnt_copos = getArguments().getInt("copos", 0);
                    }

                    if (qnt_copos == 0) {
                        Toast.makeText(getActivity(), "Beba mais água!", Toast.LENGTH_SHORT).show();
                    } else if (qnt_copos <= 1) {
                        Toast.makeText(getActivity(), "Parabéns continue assim!", Toast.LENGTH_SHORT).show();
                    }

                    NavController navController = NavHostFragment.findNavController(Perfil.this);
                    navController.navigateUp();
                }
            });
        }

        return view;
    }

}