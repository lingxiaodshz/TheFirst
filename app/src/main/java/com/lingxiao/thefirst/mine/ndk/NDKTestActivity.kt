package com.lingxiao.thefirst.mine.ndk

import android.os.Bundle
import android.widget.TextView
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

/**
 * Created by Administrator on 2018/10/23.
 */
class NDKTestActivity : BaseActivity() {
//    @BindView(R.id.tv_text)
//    var mTvText: TextView

    override fun getLayoutResource(): Int {
        return R.layout.activity_ndk_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        var tvText = findViewById<TextView>(R.id.tv_text)
        tvText.text = HelloJni.getCString()
    }

}