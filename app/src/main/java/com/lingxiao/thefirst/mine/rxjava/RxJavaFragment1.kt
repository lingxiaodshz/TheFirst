package com.lingxiao.thefirst.mine.rxjava

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/12/28.
 */
class RxJavaFragment1 : BaseFragment() {
    companion object {
        fun newInstance(): BaseFragment {
            var fragment = RxJavaFragment1()
            return fragment
        }
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout
    }

    override fun initView(view: View?) {
    }

    override fun initContent() {
        // 观察者
        var subscriber: Subscriber<String> = object : Subscriber<String>(){
            override fun onNext(t: String?) {
                if (TextUtils.isEmpty(t)) {
                    Log.e(TAG, "onNext")
                } else {
                    Log.e(TAG, t)
                }
            }

            override fun onCompleted() {
                Log.e(TAG, "onCompleted")
            }

            override fun onError(e: Throwable?) {
                Log.e(TAG, "onError")
            }

            override fun onStart() {
                Log.e(TAG, "onStart")
            }
        }

        // 被观察者
        var observable: Observable<String> = Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>?) {
                subscriber.onNext("test")
                subscriber.onNext("test2")
                subscriber.onCompleted()
            }
        })
        // 被观察者产生事件，观察者接收事件并给出响应动作，被观察者订阅观察者
        // subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
        // observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
        observable
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber)

    }
}