package com.catshouse.gerenciadordetarefas.ui.TarefasAbertas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.catshouse.gerenciadordetarefas.R;
import com.catshouse.gerenciadordetarefas.helper.TarefaDAO;

import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class AbertasFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<Tarefa> listaDeTarefas = new ArrayList<>();
    private Button botaoCheck;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_abertas, container, false);
        botaoCheck = view.findViewById(R.id.buttonCheck);
        recyclerView = view.findViewById(R.id.recyclerViewConcluidas);

        //código comentado abaixo é um listener para a lista, porém não gostei da reação dele
        //vou deixar ele aqui caso queira mudar algo no futuro
       /* recyclerView.addOnItemTouchListener(new
                RecyclerItemClickListener(getContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Tarefa tarefaSelecionada = listaDeTarefas.get(position);
                        Intent intent = new Intent(getContext(), AdicionarTarefaActivity.class);
                        intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                        getContext().startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
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
                }
        ));*/



        return view ;
    }


    @Override
    public void onStart() {
        super.onStart();
        //O METODO ABAIXO É CHAMADO PARA ATUALIZAR A LISTA SEMPRE QUE O USUÁRIO ALTERAR ALGO
        carregarListaDeTarefas();


    }

    public void carregarListaDeTarefas (){

        TarefaDAO tarefaDAO = new TarefaDAO(getContext());
        listaDeTarefas = tarefaDAO.listar();
        AdapterAbertas adapterAbertas = new AdapterAbertas(listaDeTarefas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterAbertas);
    }


}