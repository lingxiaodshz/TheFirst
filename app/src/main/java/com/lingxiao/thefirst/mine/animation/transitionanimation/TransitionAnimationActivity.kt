package com.lingxiao.thefirst.mine.animation.transitionanimation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_transition_animation.*

class TransitionAnimationActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutResource(): Int {
        return R.layout.activity_transition_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_fade.setOnClickListener(this)
        tv_explode.setOnClickListener(this)
        tv_slide.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_fade ->
                startActivity(Intent(mContext, TransitionFadeActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionAnimationActivity).toBundle())
            R.id.tv_explode ->
                startActivity(Intent(mContext, TransitionExplodeActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionAnimationActivity).toBundle())
        /**
         *  转场动画应用在将要跳转的activity中的view设置transitionName属性，该属性需要设置string
         *  然后跳转时将某个需要过度的view用Pair创建一个Pair<View, String>对象，view是过度的view，string是transitionName中定义的
         *  这样即可过度
         */
            R.id.tv_slide -> {
                startActivity(Intent(mContext, TransitionSlideActivity::class.java),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                                Pair.create(header1, "share element header imageview")).toBundle())
            }
        }
    }
}