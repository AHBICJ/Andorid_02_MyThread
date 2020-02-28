package com.example.mythread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.TreeMap;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MultiThreadFragment extends Fragment {
    private TextView countUp;
    private TextView countDown;
    private Button btnStart;
    private Button btnReturn;
    private CountUp up;
    private CountDown down;
    private int upnum=0,downnum=1000;
    boolean is_start = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CountDown.COUNTDOWNFLAG:
                    if (downnum>=0){
                        countDown.setText(""+downnum--);
                    }
                    else{
                        down.exit = true;
                    }
                    break;
                case CountUp.COUNTUPFLAG:
                    if (upnum<=1000){
                        countUp.setText(""+upnum++);
                    }
                    else {
                        up.exit = true;
                    }
                    break;
            }
            if (down.exit && up.exit){
                is_start = false;
                btnStart.setText("开始");
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countUp = (TextView) getView().findViewById(R.id.countUp);
        countDown = (TextView) getView().findViewById(R.id.countDown);


        btnStart = (Button) getView().findViewById(R.id.btn_start);
        btnStart.setOnClickListener(v->{

            if (!is_start) {
                up = new CountUp(handler);
                down = new CountDown(handler);
                up.start();
                down.start();
                is_start = true;
                btnStart.setText("暂停");
            }
            else{
                up.exit = true;
                down.exit = true;
                is_start = false;
                btnStart.setText("开始");
            }
        });
        btnReturn = (Button) getView().findViewById(R.id.btn_return);
        btnReturn.setOnClickListener(v->{
            // stop thread
            if (down!=null) down.exit = true;
            if (up!=null) up.exit = true;
            // navigation
            Navigation.findNavController(view).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (down!=null) down.exit = true;
        if (up!=null) up.exit = true;
    }
}