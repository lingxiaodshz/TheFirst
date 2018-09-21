package com.lingxiao.thefirst.mine.animation.transitionanimation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

public class TransitionExplodeActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_transition_explode;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initAnimation() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
    }
}
