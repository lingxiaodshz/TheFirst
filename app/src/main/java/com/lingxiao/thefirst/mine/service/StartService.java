package com.lingxiao.thefirst.mine.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * @author luckw
 * @date 2020/5/22
 */
public class StartService extends Service {
    @Override
    public void onCreate() {
        Log.e("StartService", "StartService====" + "onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("StartService", "StartService====" + "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("StartService", "StartService====" + "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("StartService", "StartService====" + "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.e("StartService", "StartService====" + "onDestroy");
        super.onDestroy();
    }
}
