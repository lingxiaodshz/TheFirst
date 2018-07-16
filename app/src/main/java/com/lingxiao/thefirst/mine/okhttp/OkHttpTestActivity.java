package com.lingxiao.thefirst.mine.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTestActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView mTvContent;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String json = (String) msg.obj;
                mTvContent.setText(json);
            }
        }
    };


    @Override
    public int getLayoutResource() {
        return R.layout.activity_okhttp_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("OKHttpTest");

    }

    @Override
    protected void handleData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String url = "http://apis.juhe.cn/lottery/types";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = response.body().string();
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
