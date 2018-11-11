package com.lingxiao.thefirst.mine.materialdesign

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.ClazzBean
import com.lingxiao.thefirst.mine.materialdesign.snackbar.SnackbarActivity
import com.lingxiao.thefirst.mine.materialdesign.tablayout.TabLayoutActivity
import com.lingxiao.thefirst.mine.materialdesign.textinputlayout.TextInputLayoutActivity
import kotlinx.android.synthetic.main.activity_material_design.*

/**
 * Created by Administrator on 2018/11/6.
 */
class MaterialDesignActivity : BaseActivity() {
    var mList = mutableListOf<ClazzBean>()

    override fun getLayoutResource(): Int {
        return R.layout.activity_material_design
    }

    override fun initView(savedInstanceState: Bundle?) {
        recycler_view.layoutManager = LinearLayoutManager(mContext)
        recycler_view.addItemDecoration(DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL))

        recycler_view.adapter = MaterialDesignAdapter(mContext, mList)
    }

    override fun initData() {
        var bean01 = ClazzBean()
        bean01.desc = "Snackbar"
        bean01.clazz = SnackbarActivity::class.java
        mList.add(bean01)

        var bean02 = ClazzBean()
        bean02.desc = "TextInputLayout"
        bean02.clazz = TextInputLayoutActivity::class.java
        mList.add(bean02)

        var bean03 = ClazzBean()
        bean03.desc = "TabLayout"
        bean03.clazz = TabLayoutActivity::class.java
        mList.add(bean03)
    }
}