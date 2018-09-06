package com.lingxiao.thefirst.mine.animation.propertyanimation

import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class PropertyAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_property_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("PropertyAnimation")
    }

}