package com.lingxiao.thefirst.mine.loadgif

import android.os.Bundle
import com.bumptech.glide.Glide
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_load_gif.*

class LoadGifActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_load_gif
    }

    override fun initView(savedInstanceState: Bundle?) {
        Glide.with(this)
                .load(R.drawable.gif_drawable)
                .into(iv_gif)
    }

}