package com.catshouse.gerenciadordetarefas.ui.Instruções;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InstruçõesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InstruçõesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("INSTRUÇÕES \n" + " \n" +
                "Para adicionar uma tarefa deve-se pressionar o botão com o sinal de +. \n" +
                "Após isso abrirá uma janela com os campos de adição.\n" +
                "Depois de preenchidos os campos, deverá pressionar o botão salvar que estará na barra superior da janela \n"

        + "Caso queira editar a tarefa salva, deverá dar um toque curto na tarefa desejada. \n" +
                "Caso queira excluir, deverá dar um toque longo.\n" + "Para dar a tarefa como concluida, deverá pressionar o botão " +
                "check\n" + "Então, caso concluída, a tarefa será encaminhada para a área de tarefas concluídas." +
                "\n \n" + "Desenvolvido por Cats House Development.");

    }

    public LiveData<String> getText() {
        return mText;
    }
}