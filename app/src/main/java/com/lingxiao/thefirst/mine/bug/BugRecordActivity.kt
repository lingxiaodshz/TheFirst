package com.lingxiao.thefirst.mine.bug

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import com.lingxiao.thefirst.mine.bug.textviewhtml.TextViewHtmlActivity
import kotlinx.android.synthetic.main.activity_bug_record.*

/**
 * Created by Administrator on 2018/11/5.
 */
class BugRecordActivity : BaseActivity() {
    var mList = mutableListOf<ClazzBean>()

    override fun getLayoutResource(): Int {
        return R.layout.activity_bug_record
    }

    override fun initView(savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(mContext)
        recycler_view.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = BugRecordAdapter(mContext, mList)
    }

    override fun initData() {
        var clazzBean01 = ClazzBean()
        clazzBean01.clazz = TextViewHtmlActivity::class.java
        clazzBean01.desc = "TextViewHtml"
        mList.add(clazzBean01)
    }

}