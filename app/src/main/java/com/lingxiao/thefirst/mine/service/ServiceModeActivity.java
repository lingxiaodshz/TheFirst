package com.lingxiao.thefirst.mine.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author luckw
 * @date 2020/5/22
 */
public class ServiceModeActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_service_mode;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_bind, R.id.tv_start, R.id.tv_stop_service, R.id.tv_unbind_service})
    void onClick1(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                startService(new Intent(this, StartService.class));
                break;
            case R.id.tv_stop_service:
                stopService(new Intent(this, StartService.class));
                break;
            case R.id.tv_bind:
                bindService(new Intent(this, BindService.class), connection, BIND_AUTO_CREATE);
                break;
            case R.id.tv_unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //系统调用这个来传送在service的onBind()中返回的IBinder．
            Log.e("BindService", "BindService====" + "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Android系统在同service的连接意外丢失时调用这个．比如当service崩溃了或被强杀了．
            // 当客户端解除绑定时，这个方法不会被调用．调用unbindService方法时不会调用
            Log.e("BindService", "BindService====" + "onServiceDisconnected");
        }
    };
}
