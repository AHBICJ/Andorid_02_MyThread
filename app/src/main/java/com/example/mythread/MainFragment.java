package com.example.mythread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private Button btnMulti;
    private Button btnSingle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnMulti = (Button) getView().findViewById(R.id.btn_multi);
        btnSingle = (Button) getView().findViewById(R.id.btn_single);
        btnSingle.setOnClickListener(v->{
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_singleThreadFragment);
        });
        btnMulti.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_multiThreadFragment);
        });
    }
}