package com.lingxiao.thefirst.mine.rxjava

import android.util.Log
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import rx.Observable
import rx.functions.Action1
import rx.functions.Func1
import rx.functions.Func2
import rx.schedulers.Schedulers

/**
 * Created by Administrator on 2018/12/28.
 */
class RxJavaFragment3 : BaseFragment() {
    companion object {
        fun newInstance(): BaseFragment {
            var fragment = RxJavaFragment3()
            return fragment
        }
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout
    }

    override fun initView(view: View?) {
    }

    override fun initContent() {

        //--------------------------------------创建操作符---------------------------------------------

        // 每个3秒就会执行一次Log，创建时不执行
//        Observable.interval(3,TimeUnit.SECONDS)
//                .subscribe(object : Action1<Long> {
//                    override fun call(t: Long?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        // 指定范围的整数序列的Observable，可以拿来替代for循环
//        Observable.range(0,5)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //重复发射特定数据的Observable
//        Observable.range(0,3)
//                .repeat(3)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })


        //--------------------------------------变换操作符---------------------------------------------

        //map操作符通过指定一个Func对象，将Observable转换为一个新的Observable对象并发射，观察者将收到新的Observable处理
//        Observable.just("itachi85")
//                .map(object : Func1<String, String> {
//                    override fun call(t: String?): String {
//                        return "http;//blog.csdn.net/" + t
//                    }
//                })
//                .subscribe(object : Action1<String> {
//                    override fun call(t: String?) {
//                        Log.e(TAG, t)
//                    }
//                })

        //flatMap操作符将Observable发射的数据集合变换为Observable集合，然后将这些Observable发射的数据
        //平坦化地放进一个单独的 Observable
//        var list = mutableListOf<String>()
//        list.add("itachi81")
//        list.add("itachi82")
//        list.add("itachi83")
//        list.add("itachi84")
//        Observable.from(list)
//                .flatMap(object : Func1<String, Observable<String>> {
//                    override fun call(t: String?): Observable<String> {
//                        return Observable.just("http;//blog.csdn.net/" + t)
//                    }
//
//                })
//                .subscribe(object : Action1<String> {
//                    override fun call(t: String?) {
//                        Log.e(TAG, t)
//                    }
//                })

        //flatMapIterable操作符可以将数据包装成Iterable，在Iterable中我们就可以对数据进行处理了
//        Observable.just(1, 2, 3)
//                .flatMapIterable(object : Func1<Int, Iterable<Int>> {
//                    override fun call(t: Int?): Iterable<Int> {
//                        var list = mutableListOf<Int>()
//                        t?.plus(10)?.let { list.add(it) }
//                        return list
//                    }
//                })
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })


        //buffer操作符将源Observable变换为一个新的Observable，这个新的Observable每次发射一组列表值而不
        //是一个一个发射
//        Observable.just(1, 2, 3, 4, 5, 6, 7)
//                .buffer(3)
//                .subscribe(object : Action1<List<Int>> {
//                    override fun call(t: List<Int>?) {
//                        Log.e(TAG, "-------------------------------")
//                        if (t != null) {
//                            for (i in t) {
//                                Log.e(TAG, "$i")
//                            }
//                        }
//                    }
//                })


        //--------------------------------------过滤操作符---------------------------------------------

        // filter操作符是对源Observable产生的结果自定义规则进行过滤，只有满足条件的结果才会提交给订阅者
//        Observable.just(1, 2, 3, 4, 5)
//                .filter(object : Func1<Int, Boolean> {
//                    override fun call(t: Int): Boolean {
//                        return t > 3
//                    }
//                })
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        // elementAt操作符用来返回指定位置的数据
//        Observable.just(1, 2, 3, 4, 5)
//                .elementAt(2)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //distinct 操作符用来去重，其只允许还没有发射过的数据项通过
//        Observable.just(1, 1, 2, 3, 2, 3)
//                .distinct()
//                .subscribe(object : Observer<Int> {
//                    override fun onError(e: Throwable?) {
//                    }
//                    override fun onNext(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                    override fun onCompleted() {
//                        Log.e(TAG, "onCompleted")
//                    }
//                })

        //skip操作符将源Observable发射的数据过滤掉前n项；而take操作符则只取前n项；
        // 另外还有skipLast和takeLast操作符，则是从后面进行过滤操作
//        Observable.just(1, 2, 3, 4)
//                .skip(2)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //ignoreElements操作符忽略所有源Observable产生的结果，只把Observable的onCompleted和onError事件通知给订阅者
//        Observable.just(1, 1, 2, 3, 2, 3)
//                .ignoreElements()
//                .subscribe(object : Observer<Int> {
//                    override fun onError(e: Throwable?) {
//                        Log.e(TAG, "onError")
//                    }
//
//                    override fun onNext(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//
//                    override fun onCompleted() {
//                        Log.e(TAG, "onCompleted")
//                    }
//                })


        //--------------------------------------组合操作符---------------------------------------------

        //startWith操作符会在源Observable发射的数据前面插上一些数据
//        Observable.just(3, 4)
//                .startWith(1, 2)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //merge操作符将多个Observable合并到一个Observable中进行发射，merge可能会让合并的Observable发射的数据交错
//        var observable1 = Observable.just(1, 2, 3)
//                .subscribeOn(Schedulers.io())
//        var observable2 = Observable.just(4, 5, 6)
//        Observable.merge(observable1, observable2)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //将多个 Obserbavle 发射的数据进行合并发射。concat 严格按照顺序发射数据，前一个Observable没发射
        //完成是不会发射后一个Observable的数据的。注意concat和merge的区别
//        var observable3 = Observable.just(1, 2, 3)
//                .subscribeOn(Schedulers.io())
//        var observable4 = Observable.just(4, 5, 6)
//        Observable.concat(observable3, observable4)
//                .subscribe(object : Action1<Int> {
//                    override fun call(t: Int?) {
//                        Log.e(TAG, "$t")
//                    }
//                })

        //zip操作符合并两个或者多个Observable发射出的数据项，根据指定的函数变换它们，并发射一个新值。
//        var observable5 = Observable.just(1, 2, 3)
//                .subscribeOn(Schedulers.io())
//        var observable6 = Observable.just("a", "b", "c")
//
//        Observable.zip(observable5, observable6, object : Func2<Int, String, String> {
//            override fun call(t1: Int?, t2: String?): String {
//                return "$t1" + t2
//            }
//        }).subscribe(object : Action1<String> {
//            override fun call(t: String?) {
//                Log.e(TAG, "$t")
//            }
//        })


        //--------------------------------------辅助操作符---------------------------------------------

    }
}