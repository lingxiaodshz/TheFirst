package com.lingxiao.thefirst.mine.animation.transitionanimation

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.TransitionInflater
import com.lingxiao.thefirst.BuildConfig
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class TransitionFadeActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_transition_fade
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initAnimation() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade)
            window.exitTransition = fade
        }
    }
}