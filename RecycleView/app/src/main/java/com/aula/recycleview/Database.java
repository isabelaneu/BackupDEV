package com.aula.recycleview;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.List;

public class Database {
    public Database(){}

    public void salvar(Nota agrnota, Context c){
//        abrir o database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (agrnota.getId() == 0){
//            obter o proximo id para nota
            db.collection("CountersID").document("nota_id").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    int id = 1;
                    if (task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();
                        if (doc.exists()){
                            id = doc.getLong("id").intValue() + 1;
                        }
                        agrnota.setId(id);

//                        atualizar o id
                        db.collection("CountersID").document("nota_id").update("id", id);

//                        inserir nova nota
                        db.collection("listaDeNotas").document(String.valueOf(agrnota.getId())).set(agrnota);
                        Toast.makeText(c, "Salva com sucesso", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
//            update nota
            db.collection("listaDeNotas").document(String.valueOf(agrnota.getId())).set(agrnota);
            Toast.makeText(c, "Salva com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public void remover(Nota agrnota, Context c){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("listaDeNotas").document(String.valueOf(agrnota.getId())).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(c, "Removida com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void listar(List<Nota> argLista, NotaAdapter agrAdapter, Context c){
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        //        ativar Real Time database
        database.collection("listaDeNotas").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    System.out.printf("Deu RUIM!!"+ error.getMessage());
                    return;
                }
                assert value != null;
                argLista.clear();
                for (DocumentSnapshot documento : value.getDocuments()) {
                    Nota nota = documento.toObject(Nota.class);
                    argLista.add(nota);
                }
                agrAdapter.notifyDataSetChanged();
            }
        });
    }
}
