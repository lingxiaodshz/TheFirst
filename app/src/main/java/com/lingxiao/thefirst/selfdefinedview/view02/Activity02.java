package com.lingxiao.thefirst.selfdefinedview.view02;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

/**
 * 区域绘制
 * Created by Administrator on 2018/3/23.
 */

public class Activity02 extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_02;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("区域绘制");
    }
}
