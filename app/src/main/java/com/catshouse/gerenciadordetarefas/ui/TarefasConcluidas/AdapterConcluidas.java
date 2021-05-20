package com.catshouse.gerenciadordetarefas.ui.TarefasConcluidas;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catshouse.gerenciadordetarefas.R;

import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.List;


public class AdapterConcluidas extends RecyclerView.Adapter<AdapterConcluidas.MyViewHolder> {

    private List<Tarefa> listaDeTarefas;
    private RecyclerView recyclerView;


    //CONSTRUTOR PEGA A LISTA
    public AdapterConcluidas(List<Tarefa> listaDeTarefas) {
        this.listaDeTarefas = listaDeTarefas;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista  = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lista_exibicao_concluidas,
                parent,
                false
        );

        return new MyViewHolder(itemLista);
    }
    //O OBJETO TAREFA PEGA A POSIÇAO DO ITEM
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = listaDeTarefas.get(position);
        String nomeDatarefa = tarefa.getTarefa();

        holder.tarefa.setText(nomeDatarefa);
        holder.imagemDaTarefa.setImageResource(R.drawable.ic_baseline_check);


    }


    @Override
    public int getItemCount() {
        return this.listaDeTarefas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tarefa;
        private ImageView imagemDaTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textViewNomeTarefa);
            imagemDaTarefa = itemView.findViewById(R.id.imageViewTarefa);



        }


    }

}
