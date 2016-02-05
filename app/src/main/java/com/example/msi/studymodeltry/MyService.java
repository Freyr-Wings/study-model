package com.example.msi.studymodeltry;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import java.util.List;

/**
 * Created by msi on 2016/2/4.
 */
public class MyService extends Service {
    boolean stopthread = false;
    final Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            while(!stopthread){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(getForeground());
                if (getForeground() == ""){
//                    第零种
                    Intent intent = new Intent(MyService.this, StudyModel.class);
//                    Intent intent=new Intent();
                    //第一种跳回的方法
//                    intent.setClassName("com.example.msi.studymodeltry", "com.example.msi.studymodeltry.StudyModel");
//                    //第二种
//                    ComponentName component=new ComponentName("com.example.msi.studymodeltry", "com.example.msi.studymodeltry.StudyModel");
//                    intent.setComponent(component);
//                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(intent);
                }
//                handler.postDelayed(this, 2000);
            }

        }

    };
    @Override
    public IBinder onBind(Intent arg0){
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        stopthread=false;
        new Thread(runnable).start();
//        handler.postDelayed(runnable,500);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("--------going to stop---------");
        stopthread=true;
        super.onDestroy();
    }

    public String getForeground() {
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        //获取到当前所有进程
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();
        if (processInfoList ==null || processInfoList.isEmpty()) {
            return "";
        }
        //遍历进程列表，找到第一个前台进程
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return processInfo.processName;
            }
        }
        return "";
    }
}
