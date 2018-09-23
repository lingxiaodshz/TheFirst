package com.lingxiao.thefirst.mine.animation.viewstateanimatioln

import android.os.Build
import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_view_state_animation.*

class ViewStateAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_view_state_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tv_view_state.setBackgroundResource(R.drawable.animation_view_state)

        }
    }

}