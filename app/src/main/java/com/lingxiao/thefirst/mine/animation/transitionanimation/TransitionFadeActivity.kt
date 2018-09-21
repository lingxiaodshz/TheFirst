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
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations()
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
        var fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade)
        window.exitTransition = fade
    }
}