package com.lingxiao.thefirst;

import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;

import butterknife.BindView;

public class TestFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    public static TestFragment newInsatanc() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mTvHint.setText(getClass().getSimpleName());
    }
}