package com.lingxiao.thefirst.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder mUnbinder;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutResourceID() != 0) {
            mView = inflater.inflate(getLayoutResourceID(), container, false);
        } else {
            throw new RuntimeException("layout ID is null");
        }
        mUnbinder = ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getLayoutResourceID();

    protected abstract void initView();
}
