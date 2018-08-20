package com.lingxiao.thefirst.mine.nestedscroll

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_nested_scrolling.*

class NestedScrollActivity : BaseActivity() {

    private var mList: MutableList<String> = mutableListOf()

    override fun getLayoutResource(): Int {
        return R.layout.activity_nested_scrolling
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("NestedScrollTest")
        // 注意此处必须取消RecyclerView的焦点，否则进入页面不在顶部
        recycler_view.isFocusable = false

        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")
        mList.add("NestedScrollTest")

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))
        recycler_view.adapter = NestedAdapter(this, mList)


    }
}