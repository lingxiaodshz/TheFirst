package com.lingxiao.thefirst.mine.animation.transitionanimation

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.Slide
import android.view.Gravity
import com.lingxiao.thefirst.BuildConfig
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class TransitionSlideActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_transition_slide
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initAnimation() {
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
//        var fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
//        window.enterTransition = fade
        val slide = Slide()
        slide.duration = 2000
        slide.slideEdge = Gravity.BOTTOM
        window.enterTransition = slide
    }
}