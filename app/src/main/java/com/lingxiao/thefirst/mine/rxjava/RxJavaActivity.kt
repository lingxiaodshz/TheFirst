package com.lingxiao.thefirst.mine.rxjava

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.mine.FragmentBean
import kotlinx.android.synthetic.main.activity_rx_java.*

/**
 * Created by Administrator on 2018/12/28.
 */

class RxJavaActivity : BaseActivity() {
    private var mBeans = mutableListOf<FragmentBean>()

    override fun getLayoutResource(): Int {
        return R.layout.activity_rx_java
    }

    override fun initView(savedInstanceState: Bundle?) {
        tab_layout.setupWithViewPager(viewpager)
        viewpager.adapter = RxAdapter(supportFragmentManager, this, mBeans)
    }

    override fun initData() {
        mBeans.add(FragmentBean("基本应用", RxJavaFragment1.newInstance()))
        mBeans.add(FragmentBean("Action", RxJavaFragment2.newInstance()))
        mBeans.add(FragmentBean("操作符", RxJavaFragment3.newInstance()))
    }


    class RxAdapter(fm: FragmentManager, context: Context, beans: MutableList<FragmentBean>) : FragmentPagerAdapter(fm) {
        private var beans = beans

        override fun getItem(position: Int): Fragment {
            return beans[position].fragment
        }

        override fun getCount(): Int {
            return beans.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return beans[position].title
        }

    }

}