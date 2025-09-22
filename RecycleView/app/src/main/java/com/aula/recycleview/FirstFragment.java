package com.aula.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aula.recycleview.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    List<Nota> listaNota = new ArrayList<>();
    private Database database;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Carregar dados hipotéticos
//        listaNota.add(new Nota("Nota 1", "Descrição 1"));
//        listaNota.add(new Nota("Nota 2", "Descrição da nota 2"));
//        listaNota.add(new Nota("Nota 3", "Descrição da nota 3"));
//        listaNota.add(new Nota("Nota 4", "Descrição"));
//        listaNota.add(new Nota("Nota 5", "Descrição da nota 5"));
//        listaNota.add(new Nota("Nota 6", "Descrição 6"));
//        listaNota.add(new Nota("Nota 7", "Descrição da nota 7"));
//        listaNota.add(new Nota("Nota 8", "Descrição"));

//        configuar o recycleview
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));

//        configurar o adapter
        NotaAdapter adapter = new NotaAdapter(listaNota);
        binding.rv.setAdapter(adapter);

//        Exemplos de Database - Firebase
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//
////        Adicionando Dados
////        SET -> se não existe ele cria, se ele já existe, atualiza
//        database.collection("listaDeNotas").document("1").set(new Nota("Nota 1", "Descrição 1"))
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getContext(), "Salvo com sucesso", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//        database.collection("listaDeNotas").document("2").set(new Nota("Notaaa", "Descriçãooo"));
//        database.collection("listaDeNotas").document("3").set(new Nota("Nota 3", "Descrição da nota 3"));
//
////        Alterando Dados
//        database.collection("listaDeNotas").document("1").set(new Nota("Nota alterada", "Descrição alterada"));
//
////        deletando Dados
//        database.collection("listaDeNotas").document("3").delete();
//
////        Gerar id documento automático
//        database.collection("listaDeNotas").document().set(new Nota("Nota 4", "Descrição da nota 4"));
//
////        Recuperar dados
//        database.collection("listaDeNotas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                for (DocumentSnapshot documento : task.getResult().getDocuments()) {
//                    Nota nota = documento.toObject(Nota.class);
//                    listaNota.add(nota);
//                    binding.rv.getAdapter().notifyDataSetChanged();
//                }
//            }
//        });
//
////        ativar Real Time database
////        database.collection("listaDeNotas").addSnapshotListener(new EventListener<QuerySnapshot>() {
////            @Override
////            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
////                if (error != null) {
////                    System.out.printf("Deu RUIM!!"+ error.getMessage());
////                    return;
////                }
////                assert value != null;
////                listaNota.clear();
////                for (DocumentSnapshot documento : value.getDocuments()) {
////                    Nota nota = documento.toObject(Nota.class);
////                    listaNota.add(nota);
////                    binding.rv.getAdapter().notifyDataSetChanged();
////                }
////            }
//        );

//        usando a classe database
        database = new Database();
        database.listar(listaNota, adapter, getContext());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void carregarDados(Nota arg){
//        O zero add em cima
//        listaNota.add(0,arg);
//        binding.rv.getAdapter().notifyDataSetChanged();

        database.salvar(arg,getContext());
    }

}