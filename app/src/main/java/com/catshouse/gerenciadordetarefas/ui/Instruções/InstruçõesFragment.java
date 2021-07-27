package com.catshouse.gerenciadordetarefas.ui.Instruções;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.catshouse.gerenciadordetarefas.R;

public class InstruçõesFragment extends Fragment {

    private InstruçõesViewModel instruçõesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        instruçõesViewModel =
                new ViewModelProvider(this).get(InstruçõesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_instrucoes, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        instruçõesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}