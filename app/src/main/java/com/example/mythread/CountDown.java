package com.example.mythread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CountDown extends Thread {
    public volatile boolean exit = false;
    public static final int COUNTDOWNFLAG = 0xFF02;
    public Handler handler;
    public CountDown(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        while(!exit){
            try{
                Message msg = new Message();
                msg.what = COUNTDOWNFLAG;
                handler.sendMessage(msg);
                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
