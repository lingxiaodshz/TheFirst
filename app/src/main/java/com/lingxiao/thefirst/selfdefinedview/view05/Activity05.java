package com.lingxiao.thefirst.selfdefinedview.view05;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class Activity05 extends BaseActivity {
    private View05 view05;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_05;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("Paint之ColorMatrix与滤镜效果");
        view05 = findViewById(R.id.view05);
    }
}
