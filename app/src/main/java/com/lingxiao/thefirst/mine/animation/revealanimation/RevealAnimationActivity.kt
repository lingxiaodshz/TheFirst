package com.lingxiao.thefirst.mine.animation.revealanimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_reveal_animation.*

class RevealAnimationActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_reveal_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        flag = (iv_dog.visibility == View.VISIBLE)
        fab_reveal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                launchRevealAnimation()
            }
        })

    }

    private var flag = false
    // 控制动画正在播放时不能重复播放
    private var isRunning = false
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun launchRevealAnimation() {
        if (isRunning) {
            return
        }
        isRunning = true
        val anim = iv_dog.animation
        anim?.cancel()

        val vLocation = IntArray(2)
        fab_reveal.getLocationInWindow(vLocation)
        val centerX = vLocation[0] + fab_reveal.width / 2
        val centerY = vLocation[1] + fab_reveal.height / 2

        val hypotenuse = Math.hypot(rl_layout.width.toDouble(), rl_layout.height.toDouble()).toInt()

        if (flag) {
            val circularReveal = ViewAnimationUtils.createCircularReveal(iv_dog, centerX, centerY, hypotenuse.toFloat(), 0f)
            circularReveal.duration = 2000
            circularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    iv_dog.visibility = View.GONE
                    circularReveal.removeListener(this)
                    isRunning = false
                }
            })
            circularReveal.start()
            flag = false
        } else {
            val circularReveal = ViewAnimationUtils.createCircularReveal(iv_dog, centerX, centerY, 0f, hypotenuse.toFloat())
            circularReveal.duration = 2000
            circularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    circularReveal.removeListener(this)
                    isRunning = false
                }
            })
            // 这个不能写在listener里面，否则无法显示，看不到动画效果
            iv_dog.visibility = View.VISIBLE
            circularReveal.start()
            flag = true
        }
    }
}