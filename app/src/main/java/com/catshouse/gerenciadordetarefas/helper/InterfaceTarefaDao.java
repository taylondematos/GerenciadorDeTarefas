package com.catshouse.gerenciadordetarefas.helper;

import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.List;


//INTERFACE PARA UTILIZAR NO PADRAO DAO
public interface InterfaceTarefaDao {
    boolean salvar (Tarefa tarefa);
    boolean atualizar (Tarefa tarefa);
    boolean deletar (Tarefa tarefa);
    public List<Tarefa> listar();
    public List<Tarefa> listarCheck();


}
