package com.lingxiao.thefirst.mine.animation.propertyanimation

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.ValueAnimator
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

        tv_scale.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                scale()
            }
        })
    }

    fun getXmlAnimator(): Animator {
        var animatorSet = AnimatorInflater.loadAnimator(this, R.animator.property_animator);
        animatorSet.setTarget(iv_dog)
        return animatorSet
    }

    // 属性动画缩放，注意属性动画缩放页面整体会跟着联动，证明view的属性发生了变化
    fun scale() {
        var valueAnimator = ValueAnimator.ofInt(iv_dog.width, iv_dog.width / 3)
        valueAnimator.setDuration(2000)
        valueAnimator.repeatCount = 1
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator?) {
                var current = animation!!.animatedValue as Int
                iv_dog.layoutParams.width = current
                iv_dog.layoutParams.height = current

                iv_dog.requestLayout()
            }
        })
        valueAnimator.start()
    }


}