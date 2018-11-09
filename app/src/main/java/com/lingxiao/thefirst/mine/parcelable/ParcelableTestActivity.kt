package com.lingxiao.thefirst.mine.parcelable

import android.content.Intent
import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_parcelable_test.*

/**
 * Created by Administrator on 2018/11/9.
 */
class ParcelableTestActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_parcelable_test
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_test.setOnClickListener {
            var student = StudentBean("xiaoming", 15)

            var intent = Intent(mContext, ParcelableReceiveActivity::class.java)
            intent.putExtra("stu", student)
            startActivity(intent)
        }
    }

}