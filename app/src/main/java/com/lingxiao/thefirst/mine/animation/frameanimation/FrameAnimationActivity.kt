package com.lingxiao.thefirst.mine.animation.frameanimation

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_frame_animation.*

class FrameAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_frame_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("FrameAnimation")

        tv_play.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var animationDrawable = iv_animation.background as AnimationDrawable
                animationDrawable.stop()
                animationDrawable.start()
            }

        })

    }
}