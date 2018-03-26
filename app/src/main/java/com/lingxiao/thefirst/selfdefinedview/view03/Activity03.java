package com.lingxiao.thefirst.selfdefinedview.view03;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class Activity03 extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_03;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("Canvas变换与操作");
    }
}
