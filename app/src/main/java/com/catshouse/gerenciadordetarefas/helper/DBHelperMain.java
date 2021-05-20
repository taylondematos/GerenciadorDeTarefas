package com.catshouse.gerenciadordetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelperMain extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NAME_DB="DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";


    public DBHelperMain(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    //CRIAÇAO DO DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + " nome TEXT NOT NULL, "
                + " prioridade INTEGER NOT NULL ); ";

        try{
            db.execSQL(sql);
            Log.i ("INFO DB", "Sucesso ao criar a tabela");

        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela"+
                    e.getMessage());

        }

    }

    //UPGRADE DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS + ";";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB" ,"Sucesso na atualização");

        }catch (Exception e){
            Log.i("INFO DB ", "ERRO NA ATUALIZAÇÂO " + e.getMessage());
        }
    }
}
