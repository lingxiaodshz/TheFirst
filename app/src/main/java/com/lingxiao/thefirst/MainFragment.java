package com.lingxiao.thefirst;

import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;

import butterknife.BindView;

public class MainFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    public static MainFragment newInsatanc() {
        MainFragment fragment = new MainFragment();
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
