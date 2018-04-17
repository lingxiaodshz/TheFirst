package com.lingxiao.thefirst.selfdefinedview.view06;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class Activity06 extends BaseActivity {
    private View06 mView06;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_06;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("Paint之setColorFilter");
        mView06 = findViewById(R.id.view06);
    }
}
