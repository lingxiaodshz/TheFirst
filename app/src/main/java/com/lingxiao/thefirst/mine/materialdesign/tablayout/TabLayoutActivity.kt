package com.lingxiao.thefirst.mine.materialdesign.tablayout

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity : BaseActivity() {
    private var mTitles = mutableListOf<String>()

    override fun getLayoutResource(): Int {
        return R.layout.activity_tab_layout
    }

    override fun initView(savedInstanceState: Bundle?) {
        tab_layout.setupWithViewPager(viewpager)
        viewpager.adapter = CategoryAdapter(supportFragmentManager, mContext, mTitles)
    }

    override fun initData() {
        mTitles.add("精选")
        mTitles.add("体育")
        mTitles.add("购物")
        mTitles.add("明星")
        mTitles.add("视频")
        mTitles.add("健康")
        mTitles.add("励志")
        mTitles.add("图文")
        mTitles.add("本地")
        mTitles.add("动漫")
        mTitles.add("搞笑")
    }

    class CategoryAdapter(fm: FragmentManager?, context: Context, list: MutableList<String>) : FragmentPagerAdapter(fm) {
        private var mContext = context
        private var mTitles = list


        override fun getItem(position: Int): Fragment {
            return TabLayoutFragment.newInstance(mTitles[position])
        }

        override fun getCount(): Int = mTitles.size

        override fun getPageTitle(position: Int): CharSequence? {
            return mTitles[position]
        }
    }
}