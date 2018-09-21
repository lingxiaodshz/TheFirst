package com.lingxiao.thefirst.mine.animation.transitionanimation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityOptionsCompat
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.View
import com.lingxiao.thefirst.BuildConfig
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_transition_animation.*

class TransitionAnimationActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutResource(): Int {
        return R.layout.activity_transition_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.LOLLIPOP) {
            setupWindowAnimation()
        }

        tv_fade.setOnClickListener(this)
        tv_slide.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_fade ->
                startActivity(Intent(mContext, TransitionFadeActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionAnimationActivity,null).toBundle())
            R.id.tv_slide ->
                startActivity(Intent(mContext, TransitionSlideActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionAnimationActivity).toBundle())

        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupWindowAnimation(): Unit {
        var fade = Slide()
        fade.duration = 1000
        fade.slideEdge = Gravity.BOTTOM
        window.enterTransition = fade
    }
}