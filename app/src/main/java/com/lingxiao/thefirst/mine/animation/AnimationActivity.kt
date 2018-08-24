package com.lingxiao.thefirst.mine.animation

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import com.lingxiao.thefirst.mine.animation.tweenanimation.TweenAnimationActivity
import com.lingxiao.thefirst.mine.okhttp.OkHttpTestActivity
import com.lingxiao.thefirst.mine.retrofit.RetrofitTestActivity
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
        bean02.desc = "RetrofitTest"
        bean02.clazz = RetrofitTestActivity::class.java
        mList.add(bean02)
    }
}