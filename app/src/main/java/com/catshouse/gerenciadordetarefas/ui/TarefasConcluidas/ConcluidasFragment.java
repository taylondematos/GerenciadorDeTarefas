package com.catshouse.gerenciadordetarefas.ui.TarefasConcluidas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catshouse.gerenciadordetarefas.R;
import com.catshouse.gerenciadordetarefas.helper.RecyclerItemClickListener;
import com.catshouse.gerenciadordetarefas.helper.TarefaDAO;

import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.ArrayList;
import java.util.List;


public class ConcluidasFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Button botaoCheck;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_concluidas, container, false);
        botaoCheck = view.findViewById(R.id.buttonCheck);
        recyclerView = view.findViewById(R.id.recyclerViewConcluidas);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {//não necessário

            }

            @Override
            public void onLongItemClick(View view, int position) {//para apagar
                Tarefa tarefaSelecionada = listaDeTarefas.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Confirmar Exclusão");
                dialog.setMessage("Deseja excluir a tarefa " +
                        tarefaSelecionada.getTarefa() + " ?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getContext());

                        if (tarefaDAO.deletar(tarefaSelecionada)){

                            listaDeTarefas = tarefaDAO.listar();
                            carregarListaDeTarefas();



                            Toast.makeText(getContext(),"Excluido", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getContext(),"Erro ao excluir", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                dialog.setNegativeButton("Não", null);
                //EXIBIR DIALOG
                dialog.create();
                dialog.show();



            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));





        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        carregarListaDeTarefas();
        View view = View.inflate(getContext(), R.layout.fragment_concluidas, null);



    }

    public void carregarListaDeTarefas (){

        TarefaDAO tarefaDAO = new TarefaDAO(getContext());
        listaDeTarefas = tarefaDAO.listarCheck();
        AdapterConcluidas adapterConcluidas = new AdapterConcluidas(listaDeTarefas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterConcluidas);
    }
}