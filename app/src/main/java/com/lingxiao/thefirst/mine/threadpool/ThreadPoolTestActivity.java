package com.lingxiao.thefirst.mine.threadpool;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTestActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_thread_pool_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("ThreadPool");

    }

    @Override
    protected void handleData() {
        //四种线程池
        // 1.核心线程数固定的线程池
        // ExecutorService service = Executors.newFixedThreadPool(3);

        // 2.核心线程数只有1个的线程池
        // ExecutorService service = Executors.newSingleThreadExecutor();

//        for (int i = 0; i < 15; i++) {
//            final int temp = i;
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    SystemClock.sleep(1000);
//                    Log.e("ThreadPoolTestActivity", Thread.currentThread().getName() + "--------------" + temp);
//                }
//            };
//            service.execute(runnable);
//        }

        // 3.CachedThreadPool中是没有核心线程的，但是它的最大线程数却为Integer.MAX_VALUE，
        // 另外，它是有线程超时机制的，超时时间为60秒,非核心线程超过60s之后就会被回收
        // 每当我们添加一个新任务进来的时候，如果线程池中有空闲的线程，则由该空闲的线程执行新任务，
        // 如果没有空闲线程，则创建新线程来执行任务。
//        final ExecutorService service = Executors.newCachedThreadPool();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 15; i++) {
//                    final int temp = i;
//                    Runnable runnable = new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.e("ThreadPoolTestActivity", Thread.currentThread().getName() + "--------------" + temp);
//                        }
//                    };
//                    service.execute(runnable);
//                    SystemClock.sleep(1000);
//                }
//            }
//        }).start();

        // 4.具有定期定时执行任务功能的线程池
        final ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    final int temp = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            Log.e("ThreadPoolTestActivity", Thread.currentThread().getName() + "--------------" + temp);
                        }
                    };
                    // 这个延时是针对开始执行的，不是相对于前一个的延迟
                    service.schedule(runnable, temp * 5, TimeUnit.SECONDS);
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        // 可在此增加关闭线程池功能
//        if (!service.isTerminated()) {
//            service.shutdownNow();
//        }
        super.onDestroy();
    }
}
