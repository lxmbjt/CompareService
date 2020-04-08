package com.example.thefifthhomework;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CompareService extends Service {
private Thread workThread;
int num1=0;
int num2=0;
Bundle bundle=null;
    public void onCreate(){
        super.onCreate();
        workThread=new Thread(null,backgroundWork,"WorkThread");
    }
    @Override
    public void onStart(Intent intent,int startId){
     super.onStart(intent,startId);
        bundle=intent.getExtras();
        String c1=bundle.getString("num1");
        String c2=bundle.getString("num2");

        //判断获取到的值不为空
        if(!c1.toString().equals("")&&!c2.toString().equals("")){
            num1=Integer.parseInt(c1);
            num2=Integer.parseInt(c2);
        }

        if(!workThread.isAlive()){
            workThread.start();//启动线程
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Runnable backgroundWork=new Runnable() {
        @Override
        public void run() {
            int bigNum=compare(num1,num2);//比较输入的两个数
            MainActivity.UpdateGUI(bigNum);//将较大数更新到界面中
            stopSelf();
        }
    };
    int compare(int a,int b){
        if(a>=b)
            return a;
        else
            return b;
    }
}
