package com.lingxiao.thefirst.mine.animation.tweenanimation

import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_animation_tween.*

class TweenAnimationActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_animation_tween
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("TweenAnimation")

        tv_alpha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(getAlphaAnimation())
            }
        })

        tv_scale.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(getScaleAnimation())
            }
        })

        tv_rotate.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(getRotateAnimation())
            }
        })

        tv_translation.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(getTranslateAnimation())
            }
        })

        tv_set.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(getAnimationSet())
            }
        })

        tv_alpha1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.tween_alpha))
            }
        })

        tv_scale1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.tween_scale))
            }
        })

        tv_rotate1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.tween_rotate))
            }
        })

        tv_translation1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                iv_dog.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.tween_translate))
            }
        })

        tv_set1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                iv_dog.clearAnimation()
                var set = AnimationSet(true)
                set.interpolator = AccelerateInterpolator()
                set.addAnimation(AnimationUtils.loadAnimation(mContext, R.anim.tween_alpha))
                set.addAnimation(AnimationUtils.loadAnimation(mContext, R.anim.tween_scale))
                set.addAnimation(AnimationUtils.loadAnimation(mContext, R.anim.tween_rotate))
                set.addAnimation(AnimationUtils.loadAnimation(mContext, R.anim.tween_translate))

                iv_dog.startAnimation(set)
            }
        })
    }

    fun getAlphaAnimation(): AlphaAnimation {
        var animation = AlphaAnimation(1.0f, 0.0f)
        animation.duration = 2000
        animation.repeatCount = 1 //重复次数 注意重复次数不包括第一次的次数
        animation.fillAfter = true //true表示动画结束后状态为结束后的样子，否则恢复原状，默认false
        animation.repeatMode = Animation.REVERSE
        return animation
    }

    fun getScaleAnimation(): ScaleAnimation {
        // toX和toY是相对于fromX和fromY的位移
        var animation = ScaleAnimation(1.0f, 0.5f, 1.0f, 2.0f,
                (iv_dog.width / 2).toFloat(), (iv_dog.height / 2).toFloat())
        animation.duration = 2000
        animation.repeatCount = 1
        animation.repeatMode = Animation.REVERSE
        animation.fillAfter = true
        return animation
    }

    fun getRotateAnimation(): RotateAnimation {
        var animation = RotateAnimation(0F, 270F,
                (iv_dog.width / 4).toFloat(), (iv_dog.height / 4).toFloat())
        animation.duration = 2000
        animation.repeatCount = 1
        animation.repeatMode = Animation.RESTART
        animation.fillAfter = false //恢复原始状态
        return animation
    }

    fun getTranslateAnimation(): TranslateAnimation {
        var animation = TranslateAnimation(0f, 75f, 0f, 175f)
        animation.duration = 2000
        animation.repeatCount = 1
        animation.repeatMode = Animation.RESTART
        animation.fillAfter = true //位移后不移回原始位置，保持位移后的状态
        return animation
    }

    fun getAnimationSet(): AnimationSet {
        var set = AnimationSet(true) // true表示所有的子动画用一个共同的差值器
        set.interpolator = LinearInterpolator()
        set.addAnimation(getScaleAnimation())
        set.addAnimation(getRotateAnimation())
        set.addAnimation(getTranslateAnimation())
        set.fillAfter = false
        set.repeatCount = 1
        set.repeatMode = AnimationSet.REVERSE

        return set
    }

}