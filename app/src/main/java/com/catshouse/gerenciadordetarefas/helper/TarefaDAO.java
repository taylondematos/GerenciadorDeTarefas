package com.catshouse.gerenciadordetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements InterfaceTarefaDao {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DBHelperMain dbHelperMain = new DBHelperMain(context);
        escrever = dbHelperMain.getWritableDatabase();
        ler = dbHelperMain.getReadableDatabase();
    }


    //ABAIXO ESTÃO OS METODOS UTILIZADOS PARA ALTERAR A TABELA DO DB
    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getTarefa());
        contentValues.put("prioridade", tarefa.getPrioridadeTarefa());

        try {
            escrever.insert(DBHelperMain.TABELA_TAREFAS,null,contentValues);
            Log.i("INFO", "tarefa salva comv sucesso");
        }catch (Exception e){
            Log.e("INFO","ERRO AO SALVAR TAREFA" +
                    e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getTarefa());
        contentValues.put("prioridade", tarefa.getPrioridadeTarefa());

        try{
            String [] args = {tarefa.getId().toString()};
            escrever.update(DBHelperMain.TABELA_TAREFAS, contentValues,
                    "id=?", args);
            Log.i("INFO", "Tarefa atualizada com sucesso");

        }catch (Exception e){
            Log.e("INFO", "Erro ao atualizar tarefa" +
                    e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try{
            String[] args = {tarefa.getId().toString()};
            escrever.delete(DBHelperMain.TABELA_TAREFAS,
                    "id=?", args);
            Log.i("INFO", "Tarefa removida com sucesso" );

        }catch (Exception e){
            Log.e("INFO", "ERRO AO REMOVER TAREFA");
            return false;

        }
        return true;
    }

    //LISTAR AS TAREFAS EM ABERTO NO FRAGMENT PRINCIPAL
    @Override
    public List<Tarefa> listar() {// listar os nao checkados no fragment principal
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelperMain.TABELA_TAREFAS + " WHERE prioridade >= 0 " + " ORDER BY prioridade DESC"  + ";";
        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));
            int prioridade = cursor.getInt(cursor.getColumnIndex("prioridade"));
            tarefa.setId(id);
            tarefa.setPrioridadeTarefa(prioridade);
            tarefa.setTarefa(nomeTarefa);
            tarefas.add(tarefa);
        }
        return tarefas;
    }

    //LISTAR AS TAREFAS JÁ CONCLUIDAS NO FRAGMENT CONCLUÍDAS
    @Override
    public List<Tarefa> listarCheck() {
        List<Tarefa> tarefas = new ArrayList<>();
        //NOTE QUE A DIFERENÇA ENTRE O OUTRO LISTAR ESTÁ NA LEITURA DA PRIORIDADE
        String sql = "SELECT * FROM " + DBHelperMain.TABELA_TAREFAS + " WHERE prioridade < 0 " + " ORDER BY prioridade DESC"  + ";";
        Cursor cursor = ler.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome"));
            int prioridade = cursor.getInt(cursor.getColumnIndex("prioridade"));
            tarefa.setId(id);
            tarefa.setPrioridadeTarefa(prioridade);
            tarefa.setTarefa(nomeTarefa);
            tarefas.add(tarefa);
        }



        return tarefas;
    }
}
