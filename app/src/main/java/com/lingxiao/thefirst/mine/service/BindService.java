package com.lingxiao.thefirst.mine.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * @author luckw
 * @date 2020/5/22
 */
public class BindService extends Service {
    MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        Log.e("BindService", "BindService====" + "onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e("BindService", "BindService====" + "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("BindService", "BindService====" + "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("BindService", "BindService====" + "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("BindService", "BindService====" + "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("BindService", "BindService====" + "onDestroy");
        super.onDestroy();
    }

    static class MyBinder extends Binder {
    }
}
