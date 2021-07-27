package com.catshouse.gerenciadordetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.catshouse.gerenciadordetarefas.R;
import com.catshouse.gerenciadordetarefas.helper.TarefaDAO;
import com.catshouse.gerenciadordetarefas.ui.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText entradaTarefa;
    private TextView prioridadeShow;
    private SeekBar entradaPrioridades;
    private Tarefa tarefaAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        entradaTarefa = findViewById(R.id.TextInputEditTarefa);
        entradaPrioridades = findViewById(R.id.seekBarPrioridade);
        prioridadeShow = findViewById(R.id.textViewPrioridadeShow);

        //listener do botao prioridades
        entradaPrioridades.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prioridadeShow.setText("Priority level: " + Integer.toString(entradaPrioridades.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //recuperar a tarefa vinda da main
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //configurar a caixa de texto depois recuperar tarefa da main
        if (tarefaAtual != null) {
            entradaTarefa.setText(tarefaAtual.getTarefa());
            entradaPrioridades.setProgress(tarefaAtual.getPrioridadeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //ATUALIZAÇÃO, EDIÇAO, EXCLUSAO, ADIÇAO
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvarId:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaAtual != null) {//editar
                    String nomeDaTarefa = entradaTarefa.getText().toString();
                    int prioridadeDaTarefa = entradaPrioridades.getProgress();

                    if (!nomeDaTarefa.isEmpty()) {

                        Tarefa tarefa = new Tarefa();
                        tarefa.setId(tarefaAtual.getId());
                        tarefa.setTarefa(nomeDaTarefa);
                        tarefa.setPrioridadeTarefa(prioridadeDaTarefa);

                        //atualizando
                        if (tarefaDAO.atualizar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_LONG)
                                    .show();

                        } else {
                            Toast.makeText(getApplicationContext(), "ERRO AO SALVAR", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                    } else {//salvar
                        String nomeDaTarefa = entradaTarefa.getText().toString();
                        int prioridadeDaTarefa = entradaPrioridades.getProgress();

                        if (!nomeDaTarefa.isEmpty()){
                            Tarefa tarefa = new Tarefa();
                            tarefa.setTarefa(nomeDaTarefa);
                            tarefa.setPrioridadeTarefa(prioridadeDaTarefa);
                            if (tarefaDAO.salvar(tarefa)){
                                finish();
                                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(getApplicationContext(), "Erro ao salvar", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                    break;
                }
        return super.onOptionsItemSelected(item);
        }

    }



