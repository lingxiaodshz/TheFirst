package com.lingxiao.thefirst.mine.rxjava

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_tab_layout1.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Created by Administrator on 2018/12/28.
 */
class RxJavaFragment1 : BaseFragment(){
    companion object {
        fun newInstance(): BaseFragment {
            var fragment = RxJavaFragment1()
            return fragment
        }

        var i: Int = 15
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout1
    }

    override fun initView(view: View?) {
        bt_test.setOnClickListener(this)
    }

    override fun initContent() {

    }

    fun test() {
        var subscriber:Subscriber<String> = object : Subscriber<String> {
            override fun onComplete() {
                TODO("Not yet implemented")
            }

            override fun onSubscribe(s: Subscription?) {
                TODO("Not yet implemented")
            }

            override fun onNext(t: String?) {
                TODO("Not yet implemented")
            }

            override fun onError(t: Throwable?) {
                TODO("Not yet implemented")
            }

        }
        var observer: Observer<String> = object : Observer<String> {
            override fun onNext(t: String) {
                if (TextUtils.isEmpty(t)) {
                    Log.e(TAG, "onNext")
                    tv_content.append("onNext")
                    tv_content.append("\n")
                } else {
                    Log.e(TAG, t)
                    tv_content.append(t)
                    tv_content.append("\n")
                }
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError")
                tv_content.append("onError")
                tv_content.append("\n")
            }

            override fun onComplete() {
                Log.e(TAG, "onComplete")
                tv_content.append("onComplete")
                tv_content.append("\n")
            }

            override fun onSubscribe(d: Disposable) {
                Log.e(TAG, "onSubscribe")
                tv_content.append("onSubscribe")
                tv_content.append("\n")
            }
        }

        var observable: Observable<String> = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("test")
                tv_content.append("test")
                tv_content.append("\n")
                emitter.onNext("test2")
                tv_content.append("test2")
                tv_content.append("\n")
                emitter.onComplete()
            }
        })

        observable.subscribe(observer)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_test ->
                test()
        }
    }
}