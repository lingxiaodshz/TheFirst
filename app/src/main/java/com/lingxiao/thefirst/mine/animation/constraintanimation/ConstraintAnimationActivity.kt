package com.lingxiao.thefirst.mine.animation.constraintanimation

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_constraint_animation1.*


class ConstraintAnimationActivity : BaseActivity() {

    private var first= ConstraintSet()
    private var second= ConstraintSet()
    private lateinit var firstLayout: ConstraintLayout
    var isFirst = true

    override fun getLayoutResource(): Int {
        return R.layout.activity_constraint_animation1
    }

    override fun initView(savedInstanceState: Bundle?) {
        firstLayout = findViewById(R.id.contentPanel)

        first.clone(firstLayout)
        second.clone(this, R.layout.activity_constraint_animation2)
        start.setOnClickListener {
            if (isFirst) {
                isFirst = false
                TransitionManager.beginDelayedTransition(firstLayout)
                second.applyTo(firstLayout)
            } else {
                isFirst = true
                TransitionManager.beginDelayedTransition(firstLayout)
                first.applyTo(firstLayout)
            }
        }
        button2.setOnClickListener {
            showToast("lalala~~")
        }
    }

}