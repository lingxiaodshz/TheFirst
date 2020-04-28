package com.lingxiao.thefirst.mine.rxjava

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/12/28.
 */
class RxJavaFragment2 : BaseFragment() {
    companion object {
        fun newInstance(): BaseFragment {
            var fragment = RxJavaFragment2()
            return fragment
        }
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout
    }

    override fun initView(view: View?) {
    }

    override fun initContent() {
        // 被观察者
        var observable: Observable<String> = Observable.create(object : Observable.OnSubscribe<String> {
            override fun call(t: Subscriber<in String>?) {
                t?.onNext("test")
                t?.onNext("test2")
            }
        })

        var nextAction: Action1<String> = object : Action1<String> {
            override fun call(t: String?) {
                Log.e(TAG, "RxJavaFragment2:: " + t)
            }
        }
        var errorAction:Action1<Throwable> = object : Action1<Throwable> {
            override fun call(t: Throwable?) {

            }
        }
        var completeAction: Action0 = object : Action0 {
            override fun call() {
                Log.e(TAG, "RxJavaFragment2:: complete")
            }
        }

        observable.subscribe(nextAction, errorAction, completeAction)

    }
}