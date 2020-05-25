package com.lingxiao.thefirst.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingxiao.thefirst.utils.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Unbinder mUnbinder;
    private View mView;
    protected Context mContext;
    protected String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutResourceID() != 0) {
            mView = inflater.inflate(getLayoutResourceID(), container, false);
        } else {
            throw new RuntimeException("layout ID is null");
        }
        mUnbinder = ButterKnife.bind(this, mView);
        mContext = getActivity();
        initFunc();
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(mView);
        initContent();
    }

    @Override
    public void onClick(View v) {

    }

    protected void initFunc() {
    }

    protected void initContent() {
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);
    }

    public void showToast(@StringRes int resId) {
        ToastUtil.showToast(mContext, getString(resId));
    }

    protected abstract int getLayoutResourceID();

    protected abstract void initView(View view);

}
