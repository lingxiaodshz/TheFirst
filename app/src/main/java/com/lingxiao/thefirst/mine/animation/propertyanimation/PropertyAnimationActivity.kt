package com.lingxiao.thefirst.mine.animation.propertyanimation

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_property_animation.*

class PropertyAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_property_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("PropertyAnimation")

        tv_xml.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getXmlAnimator().start()
            }
        })
    }

    fun getXmlAnimator(): Animator {
        var animatorSet = AnimatorInflater.loadAnimator(this, R.animator.property_animator);
        animatorSet.setTarget(iv_dog)
        return animatorSet
    }
}