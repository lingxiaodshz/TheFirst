package com.lingxiao.thefirst.kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class MyActivity : BaseActivity() {
    @BindView(R.id.tv_test)
    lateinit var mTvTest: TextView

    override fun getLayoutResource(): Int {
        return R.layout.activity_my_kotlin_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("Kotlin Test")
        mTvTest.text = "啦啦啦，点我"
    }

    override fun handleData() {
        super.handleData()
        showToast("test")
    }

    @OnClick(R.id.tv_test)
    fun onClick1(view: View) {
        when (view.id) {
            R.id.tv_test -> {
                showToast("我被点击了")
            }
        }
    }
}
