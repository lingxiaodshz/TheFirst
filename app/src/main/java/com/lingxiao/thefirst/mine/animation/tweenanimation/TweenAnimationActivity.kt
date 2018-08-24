package com.lingxiao.thefirst.mine.animation.tweenanimation

import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class TweenAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_animation_tween
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("TweenAnimation")

    }

}