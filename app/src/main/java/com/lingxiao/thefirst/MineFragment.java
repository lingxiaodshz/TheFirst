package com.lingxiao.thefirst;

import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;

import butterknife.BindView;

public class MineFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    public static MineFragment newInsatanc() {
        MineFragment fragment = new MineFragment();
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
