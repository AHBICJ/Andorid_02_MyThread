package com.example.mythread;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SingleThreadFragment extends Fragment {
    private TextView text;
    private Button btnStart;
    private Button btnReturn;
    private CountUp count;
//    private Count count;
    boolean is_start = false;
    int upnum=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case CountUp.COUNTUPFLAG:
                    if (upnum<=1000){
                        text.setText(""+upnum++);
                    }
                    else {
                        count.exit = true;
                        is_start = false;
                        btnStart.setText("开始");
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_thread, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = (TextView) getView().findViewById(R.id.text);
//        CountUp countUp = new CountUp();
        btnStart = (Button) getView().findViewById(R.id.btn_start);
        btnReturn = (Button) getView().findViewById(R.id.btn_return);
        btnStart.setOnClickListener(v->{
            if (!is_start) {
                count = new CountUp(handler);
                count.start();
                is_start = true;
                btnStart.setText("暂停");
            }
            else{
                count.exit = true;
                is_start = false;
                btnStart.setText("开始");
            }
        });
        btnReturn.setOnClickListener(v->{
            // stop thread
            if (is_start) count.exit = true;
            // navigation
            Navigation.findNavController(view).navigateUp();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (count!=null) count.exit = true;
    }
//    public class Count extends Thread {
//        public volatile boolean exit = false;
//        public static final int COUNTUPFLAG = 0xFF01;
//        @Override
//        public void run() {
//            while(!exit){
//                try{
//                    Log.e("AAA","AAA");
//                    Message msg = new Message();
//                    msg.what = COUNTUPFLAG;
//                    handler.sendMessage(msg);
//                    Thread.sleep(500);
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}