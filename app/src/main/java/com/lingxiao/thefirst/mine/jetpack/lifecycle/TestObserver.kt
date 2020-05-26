package com.lingxiao.thefirst.mine.jetpack.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log


/**
 * @author luckw
 * @date   2020/5/26
 */
class TestObserver : LifecycleObserver {
    private var TAG = javaClass.simpleName

    constructor(tag: String){
        TAG = tag
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        Log.d(TAG, "onCreated: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.d(TAG, "onStart: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.d(TAG, "onResume: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Log.d(TAG, "onPause: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.d(TAG, "onStop: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun customMethod() {
        Log.d(TAG, "customMethod: ")
    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny() {
//        //此方法可以有参数，但类型必须如两者之一(LifecycleOwner owner,Lifecycle.Event event)
//        Log.d(TAG, "onAny: ")
//    }
}