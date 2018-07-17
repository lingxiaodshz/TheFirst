package com.lingxiao.thefirst.mine.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;

public class RetrofitTestActivity extends BaseActivity {
    @BindView(R.id.tv_content)
    TextView mTvContent;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_retrofit_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("RetrofitTest");
    }
}
