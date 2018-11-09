package com.lingxiao.thefirst.mine.parcelable

import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_parcelable_receive.*

/**
 * Created by Administrator on 2018/11/9.
 */
class ParcelableReceiveActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_parcelable_receive
    }

    override fun initView(savedInstanceState: Bundle?) {
        var studentBean = intent.getParcelableExtra<StudentBean>("stu")

        tv_text.text = studentBean.getName() + "  " + studentBean.getAge()
    }

}