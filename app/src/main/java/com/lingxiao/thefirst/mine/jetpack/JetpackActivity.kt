package com.lingxiao.thefirst.mine.jetpack

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import com.lingxiao.thefirst.mine.jetpack.room.JetpackRoomActivity
import kotlinx.android.synthetic.main.activity_jetpack.*

/**
 * @author luckw
 * @date   2020/5/25
 */

class JetpackActivity : BaseActivity() {

    override fun initView(savedInstanceState: Bundle?) {
        var list = ArrayList<ClazzBean>()
        var clazzBean01: ClazzBean = ClazzBean()
        clazzBean01.clazz = JetpackRoomActivity::class.java
        clazzBean01.desc = "JetpackRoom"
        list.add(clazzBean01)


        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recycler_view.adapter = JetpackAdapter(this, list)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_jetpack
    }


}