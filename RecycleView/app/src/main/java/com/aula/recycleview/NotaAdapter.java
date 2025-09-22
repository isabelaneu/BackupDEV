package com.aula.recycleview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder> {


    private List<Nota> listaNota;
    private Database database;

    public NotaAdapter(List<Nota> listaNota) {
        this.listaNota = listaNota;
    }

    @NonNull
    @Override
    public NotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//         vincular o XML para careggar o RV
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NotaAdapter.ViewHolder holder, int position) {

        holder.titulo.setText(listaNota.get(position).getTitulo());
        holder.descricao.setText(listaNota.get(position).getDescricao());

        if (position%2 == 0){
//            cor rosa
            holder.fundo.setBackgroundColor(Color.parseColor("#ff69b4"));
        }else{
            holder.fundo.setBackgroundColor(Color.parseColor("#ffa8d9"));
        }

        holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//               listaNota.remove(holder.getAdapterPosition());
//               notifyItemRemoved(holder.getAdapterPosition());
//               return true;
                database = new Database();
                database.remover(listaNota.get(holder.getAdapterPosition()),v.getContext());
                return true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaNota.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        vincula o XML card com o JAVA

        TextView titulo,descricao;
        ConstraintLayout fundo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            descricao = itemView.findViewById(R.id.descricao);
            fundo = itemView.findViewById(R.id.fundo);
        }
    }
}
