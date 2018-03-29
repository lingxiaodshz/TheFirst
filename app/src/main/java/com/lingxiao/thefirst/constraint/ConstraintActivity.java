package com.lingxiao.thefirst.constraint;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

public class ConstraintActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_constraint;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("ConstraintLayout");
    }
}
