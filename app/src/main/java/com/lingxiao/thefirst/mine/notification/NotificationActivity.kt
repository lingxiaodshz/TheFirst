package com.lingxiao.thefirst.mine.notification

import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_common ->
                showToast("common")
            R.id.tv_fold ->
                showToast("fold")
            R.id.tv_hang ->
                showToast("hang")
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_notification
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_common.setOnClickListener(this)
        tv_fold.setOnClickListener(this)
        tv_hang.setOnClickListener(this)

    }

}