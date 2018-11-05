package com.lingxiao.thefirst.mine.bug

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import kotlinx.android.synthetic.main.activity_bug_record.*

/**
 * Created by Administrator on 2018/11/5.
 */
class BugRecordActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_bug_record
    }

    override fun initView(savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        recycler_view.adapter = BugRecordAdapter(this,getData())
    }

    private fun getData(): MutableList<ClazzBean> {
        var list = mutableListOf<ClazzBean>()
        return list
    }

}