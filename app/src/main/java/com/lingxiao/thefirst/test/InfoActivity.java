package com.lingxiao.thefirst.test;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

public class InfoActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitleCenter("消息中心");
    }
}
