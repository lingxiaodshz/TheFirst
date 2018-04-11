package com.lingxiao.thefirst.selfdefinedview.view04;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

/**
 * Created by Administrator on 2018/3/26.
 */

public class Activity04 extends BaseActivity {
    private View04 view04;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_04;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("图片处理");
        view04 = findViewById(R.id.view04);
    }
}
