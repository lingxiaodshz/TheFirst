package com.lingxiao.thefirst;

import android.app.Application;

import com.lingxiao.thefirst.crash.CrashHandler;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CrashHandler.getInstance().init();

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
    }
}
