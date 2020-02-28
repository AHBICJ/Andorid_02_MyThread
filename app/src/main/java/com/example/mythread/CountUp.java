package com.example.mythread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class CountUp extends Thread {
    public volatile boolean exit = false;
    public static final int COUNTUPFLAG = 0xFF01;
    public Handler handler;
    public CountUp(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        while(!exit){
            try{
                Log.e("AAA","AAA");
                Message msg = new Message();
                msg.what = COUNTUPFLAG;
                handler.sendMessage(msg);
                Thread.sleep(100);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void close(){
        exit = true;
    }
}
