package com.catshouse.gerenciadordetarefas.ui.model;

import java.io.Serializable;
//CLASSE MODELO PARA DEFINIR AS TAREFAS
public class Tarefa implements Serializable  {
    private Long id;
    private String tarefa;
    private int prioridadeTarefa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public int getPrioridadeTarefa() {

        return prioridadeTarefa;
    }

    public void setPrioridadeTarefa(int prioridadeTarefa) {
        this.prioridadeTarefa = prioridadeTarefa;
    }
}
