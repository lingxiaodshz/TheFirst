package com.lingxiao.thefirst.mine.animation

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import com.lingxiao.thefirst.mine.animation.frameanimation.FrameAnimationActivity
import com.lingxiao.thefirst.mine.animation.propertyanimation.PropertyAnimationActivity
import com.lingxiao.thefirst.mine.animation.revealanimation.RevealAnimationActivity
import com.lingxiao.thefirst.mine.animation.rippleeffect.RippleEffectActivity
import com.lingxiao.thefirst.mine.animation.tweenanimation.TweenAnimationActivity
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : BaseActivity() {
    private var mList = mutableListOf<ClazzBean>()

    override fun getLayoutResource(): Int = R.layout.activity_animation

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("AnimationAll")

        recycler_view.layoutManager = LinearLayoutManager(mContext)
        recycler_view.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))

        recycler_view.adapter = AnimationAdapter(mContext, mList)

    }

    override fun initData() {
        val bean01 = ClazzBean()
        bean01.desc = "TweenAnimation"
        bean01.clazz = TweenAnimationActivity::class.java
        mList.add(bean01)

        val bean02 = ClazzBean()
        bean02.desc = "FrameAnimation"
        bean02.clazz = FrameAnimationActivity::class.java
        mList.add(bean02)

        val bean03 = ClazzBean()
        bean03.desc = "PropertyAnimation"
        bean03.clazz = PropertyAnimationActivity::class.java
        mList.add(bean03)

        val bean04 = ClazzBean()
        bean04.desc = "RippleEffect"
        bean04.clazz = RippleEffectActivity::class.java
        mList.add(bean04)

        val bean05 = ClazzBean()
        bean05.desc = "RevealAnimation"
        bean05.clazz = RevealAnimationActivity::class.java
        mList.add(bean05)
    }
}