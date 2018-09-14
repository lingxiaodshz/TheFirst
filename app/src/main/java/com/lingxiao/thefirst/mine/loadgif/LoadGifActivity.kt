package com.lingxiao.thefirst.mine.loadgif

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_load_gif.*
import com.bumptech.glide.load.resource.bitmap.RoundedCorners



class LoadGifActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_load_gif
    }

    override fun initView(savedInstanceState: Bundle?) {
        val roundedCorners = RoundedCorners(15)
        val options = RequestOptions.bitmapTransform(roundedCorners)

        Glide.with(this)
                .load(R.drawable.gif_drawable)
                //显示圆形图片
//                .apply(RequestOptions.circleCropTransform())
                // 显示带圆角的图片
                .apply(options)
                .into(iv_gif)
    }

}