package com.lingxiao.thefirst;

import android.app.Application;

import com.lingxiao.thefirst.crash.CrashHandler;
import com.lingxiao.thefirst.utils.DensityUtil;

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

        //TODO 有待完善
        DensityUtil.setDensity(this, 360);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
    }
}
