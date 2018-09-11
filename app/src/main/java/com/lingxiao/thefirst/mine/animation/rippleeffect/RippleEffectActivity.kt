package com.lingxiao.thefirst.mine.animation.rippleeffect

import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_ripple_effect.*

class RippleEffectActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_ripple_effect
    }

    override fun initView(savedInstanceState: Bundle?) {

        tv_ripple1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
        tv_ripple2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })

    }

}