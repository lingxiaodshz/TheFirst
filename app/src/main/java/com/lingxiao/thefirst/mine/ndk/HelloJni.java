package com.lingxiao.thefirst.mine.ndk;

/**
 * Created by Administrator on 2018/10/23.
 */

public class HelloJni {
    static {
        System.loadLibrary("hellojni");
    }

    public static native String getCString();
}
