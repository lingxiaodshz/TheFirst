package com.lingxiao.thefirst.selfdefinedview.view01;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.R;

/**
 * Created by Administrator on 2018/3/16.
 */

public class Activity01 extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_01;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("基础绘制");
    }
}
