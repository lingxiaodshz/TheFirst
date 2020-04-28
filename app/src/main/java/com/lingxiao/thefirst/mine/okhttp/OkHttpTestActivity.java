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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                String url = "http://apis.juhe.cn/lottery/types";
//                Request request = new Request.Builder()
//                        .url(url)
//                        .build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    Message msg = Message.obtain();
//                    msg.what = 1;
//                    msg.obj = response.body().string();
//                    mHandler.sendMessage(msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        postRequest();
    }

    private void postRequest() {
        RequestBody requestBody = new FormBody.Builder()
                .add("ip", "59.108.54.37")
                .build();
        Request request = new Request.Builder()
                .url("http://ip.taobao.com/service/getIpInfo.php")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("onFailure");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        showToast("onResponse");
                    }
                });
            }
        });
    }
}
