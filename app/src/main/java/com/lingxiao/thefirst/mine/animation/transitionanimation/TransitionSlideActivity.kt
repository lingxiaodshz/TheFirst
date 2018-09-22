package com.lingxiao.thefirst.mine.animation.transitionanimation

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.transition.Slide
import android.view.Gravity
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class TransitionSlideActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_transition_slide
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initAnimation() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimations()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimations() {
        val slide = Slide()
        slide.duration = 500
        slide.slideEdge = Gravity.RIGHT
        window.enterTransition = slide
    }
}