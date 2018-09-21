package com.lingxiao.thefirst.base;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;
    @Nullable
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;

    protected String TAG = getClass().getSimpleName();
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        initAnimation();
        ButterKnife.bind(this);
        mContext = this;
        // 按音量键会调整不同的音量，STREAM_RING:调整铃声  STREAM_SYSTEM:调整系统  STREAM_MUSIC:调整媒体
        setVolumeControlStream(AudioManager.STREAM_RING);

        initToolbar();
        initData();
        initView(savedInstanceState);

        handleData();

    }

    protected void initAnimation(){}

    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    public void hideBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void setTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void setTitle(@StringRes int resId) {
        setTitle(getString(resId));
    }

    public void setTitleCenter(String title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        }
    }

    public void setTitleCenter(@StringRes int resId) {
        setTitleCenter(getString(resId));
    }

    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);
    }

    public void showToast(@StringRes int resId) {
        ToastUtil.showToast(mContext, getString(resId));
    }

    public abstract int getLayoutResource();

    public abstract void initView(@Nullable Bundle savedInstanceState);

    protected void initData() {
    }

    protected void handleData() {
    }
}
