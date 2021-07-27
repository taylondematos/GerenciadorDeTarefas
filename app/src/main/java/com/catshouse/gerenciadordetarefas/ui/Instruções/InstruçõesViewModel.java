package com.catshouse.gerenciadordetarefas.ui.Instruções;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InstruçõesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InstruçõesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("INSTRUCTIONS \n" + " \n" +
                        "1 - To add a task one must press the button in the lower right corner with the '+' sign. \n  " + " \n" +
                        "2 - A window will open with the fields to add the task. The tasks will be organized by the priority you " +
                "choose in the priority bar. You will be assigned a red flag " +
                "(if the priority is high), yellow (if the priority is medium) and green (if the priority is low).\n" + " \n" +
                "3 - After filling out the fields, you must press the save button that will be in the upper bar of the window.\n"

                        +  " \n" + "If you want to edit the saved task, you will have to give a short tap on the desired task. \n" + " \n" +
                        "If you want to delete the task, you must give it a long press.\n" +
                 "Then, if completed, the task will be forwarded to the completed tasks area." + " \n" + " \n" +
                        "\n" + "Developed by Cats House Development.\n");

    }

    public LiveData<String> getText() {
        return mText;
    }
}