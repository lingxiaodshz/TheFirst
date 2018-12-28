package com.lingxiao.thefirst.mine.rxjava

import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseFragment

/**
 * Created by Administrator on 2018/12/28.
 */
class RxJavaFragment : BaseFragment() {
    companion object {
        fun newInstance(): BaseFragment {
            var fragment = RxJavaFragment()
            return fragment
        }
    }

    override fun getLayoutResourceID(): Int {
        return R.layout.fragment_tab_layout
    }

    override fun initView(view: View?) {
    }

}