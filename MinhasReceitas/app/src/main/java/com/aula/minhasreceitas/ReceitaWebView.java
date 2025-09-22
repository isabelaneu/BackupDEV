package com.aula.minhasreceitas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceitaWebView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceitaWebView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceitaWebView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceitaWebView.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceitaWebView newInstance(String param1, String param2) {
        ReceitaWebView fragment = new ReceitaWebView();
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
        View view = inflater.inflate(R.layout.fragment_receita_web_view, container, false);
        
        WebView webView = view.findViewById(R.id.web);
        Bundle receitas = getArguments();
        
        if (receitas != null) {
            String nome = receitas.getString("nome");

            if ("Bolo de cenoura".equals(nome)) {
                webView.loadUrl("http://tudogostoso.com.br/receita/23-bolo-de-cenoura.html");
            } else if ("Frango grelhado ao alho e limão".equals(nome)) {
                webView.loadUrl("https://www.tudogostoso.com.br/noticias/frango-grelhado-ao-alho-e-limao-e-a-receita-facil-para-encher-de-sabor-o-seu-almoco-de-segunda-feira-a20232.htm");
            } else if ("Lasanha".equals(nome)) {
                webView.loadUrl("https://www.tudogostoso.com.br/noticias/domingo-e-o-dia-perfeito-para-preparar-essa-lasanha-incrementada-e-reunir-a-familia-em-um-almoco-incrivel-a19595.htm");
            }


        }else {
            Toast.makeText(getActivity(), "Não foi possível carregar a receita", Toast.LENGTH_SHORT).show();
        }




        return view;
    }
}