package com.catshouse.gerenciadordetarefas.ui.TarefasAbertas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.catshouse.gerenciadordetarefas.R;
import com.catshouse.gerenciadordetarefas.activity.AdicionarTarefaActivity;
import com.catshouse.gerenciadordetarefas.activity.MainActivity;
import com.catshouse.gerenciadordetarefas.helper.TarefaDAO;
import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.List;

public class AdapterAbertas extends RecyclerView.Adapter<AdapterAbertas.MyViewHolder> {

    private List<Tarefa> listaDeTarefas;
    private RecyclerView recyclerView;
    private Button botaoCheck;



    public AdapterAbertas(List<Tarefa> listaDeTarefas) {
        this.listaDeTarefas = listaDeTarefas;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista  = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lista_exibicao_abertas,
                parent,
                false
        );

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Tarefa tarefa = listaDeTarefas.get(position);
        String nomeDatarefa = tarefa.getTarefa();
        String prioridadeTarefa = Integer.toString(tarefa.getPrioridadeTarefa());

        //LISTENER DO BOTAO CHECK (BOTAO PARA CONCLUIR)
        botaoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "check", Toast.LENGTH_LONG).show();
                Tarefa tarefaCheck = listaDeTarefas.get(position);
                TarefaDAO tarefaDAO = new TarefaDAO(v.getContext());
                tarefaCheck.setTarefa(nomeDatarefa);
                tarefaCheck.setPrioridadeTarefa(-1);
                tarefaDAO.atualizar(tarefaCheck);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);


            }
        });



        //abaixo fica o listener (no adapter teve uma reação melhor do que o tradicional)
        //CLICK NORMAL
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//edição
                Tarefa tarefaSelecionada = listaDeTarefas.get(position);
                Intent intent = new Intent(v.getContext(), AdicionarTarefaActivity.class);
                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                v.getContext().startActivity(intent);



            }
        });

       //CLICK LONGO
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {//exclusao

                Tarefa tarefaSelecionada = listaDeTarefas.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getRootView().getContext());
                dialog.setTitle("Confirmar Exclusão");
                dialog.setMessage("Deseja excluir a tarefa " +
                        tarefaSelecionada.getTarefa() + " ?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(v.getContext());

                        if (tarefaDAO.deletar(tarefaSelecionada)){

                            listaDeTarefas = tarefaDAO.listar();
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            v.getContext().startActivity(intent);


                            Toast.makeText(v.getContext(),"Excluido", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(v.getContext(),"Erro ao excluir", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();

                return false;
            }
        });

       //  android:theme="@style/Theme.GerenciadorDeTarefas.AppBarOverlay"
        holder.tarefa.setText(nomeDatarefa);
        holder.prioriadeDaTarefa.setText("Prioridade: " + prioridadeTarefa);


        //A DEFINIÇÃO DAS BANDEIRAS
        //COLOQUEI UMA QUARTA OPÇÃO PARA CASO QUEIRA COLOCAR OUTRA BANDEIRA
        if (tarefa.getPrioridadeTarefa() >= 8){
            holder.imagemDaTarefa.setImageResource(R.drawable.fitavermelhapng);

        }else if (tarefa.getPrioridadeTarefa()>= 5 && tarefa.getPrioridadeTarefa() <=7){
            holder.imagemDaTarefa.setImageResource(R.drawable.fitaamarelapng);
        }else if (tarefa.getPrioridadeTarefa()>= 2 && tarefa.getPrioridadeTarefa() <=4){
            holder.imagemDaTarefa.setImageResource(R.drawable.fitaverdepng);
        }else{
            holder.imagemDaTarefa.setImageResource(R.drawable.fitaverdepng);
        }


    }

    @Override
    public int getItemCount() {

        return this.listaDeTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tarefa;
        private TextView prioriadeDaTarefa;
        private ImageView imagemDaTarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textViewNomeTarefa);
            prioriadeDaTarefa = itemView.findViewById(R.id.textViewPrioridade);
            imagemDaTarefa = itemView.findViewById(R.id.imageViewTarefa);
            botaoCheck = itemView.findViewById(R.id.buttonCheck);


        }


    }

}

