package com.lingxiao.thefirst.mine.materialdesign.tablayout

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tab_layout.*

class TabLayoutFragment : BaseFragment() {
    companion object {
        fun newInstance(content: String): TabLayoutFragment {
            var fragment = TabLayoutFragment()
            var bundle = Bundle()
            bundle.putString("content", content)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout
    }

    override fun initView(view: View) {
//        var tvContent = view.findViewById<TextView>(R.id.tv_content)
//        tvContent.text = arguments!!.getString("content")

        // 不能直接用id的原因，是因为你的xml没有载入，会导致使用id的时候会报空指针，
        // 如果需要使用，在onCreateView return view后，在onViewCreate函数中使用Id直接调用，
        // onViewCreate会在onCreateView后执行
        tv_content.text = arguments!!.getString("content")
    }

}