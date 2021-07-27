package com.catshouse.gerenciadordetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.catshouse.gerenciadordetarefas.R;

public class AberturaActivity extends AppCompatActivity implements Runnable {

    Thread thread;
    Handler handler;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AberturaActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }

    @Override
    public void run() {



    }
}