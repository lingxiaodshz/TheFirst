package com.lingxiao.thefirst.mine.rxjava

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_tab_layout2.*

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
        return R.layout.fragment_tab_layout2
    }

    override fun initView(view: View?) {
        bt_test.setOnClickListener(this)
    }

    override fun initContent() {
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_test ->
                action()
        }
    }

    @SuppressLint("CheckResult")
    private fun action() {
        var observable: Observable<String> = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                tv_content.append("subscribe")
                tv_content.append("\n")

                emitter.onNext("1")

                //注意 onError和onComplete只能执行一个
//                emitter.onError(object : Throwable("throwable") {
//                })

                emitter.onComplete()
            }
        })

        observable.subscribe(object : Consumer<String> {
            override fun accept(t: String?) {
                tv_content.append("accept  ")
                tv_content.append(t)
                tv_content.append("\n")
            }
        }, object : Consumer<Throwable> {
            override fun accept(t: Throwable?) {
                tv_content.append("accept  ")
                tv_content.append(t?.message)
                tv_content.append("\n")
            }

        }, object : Action {
            override fun run() {
                tv_content.append("run")
                tv_content.append("\n")
            }
        })
    }
}