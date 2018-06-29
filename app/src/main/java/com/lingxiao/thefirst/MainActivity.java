package com.lingxiao.thefirst;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        hideBackButton();
        setTitle("MainActivity");

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

    }

    class MyAdapter extends FragmentPagerAdapter {
        List<BaseFragment> mList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mList.add(new MainFragment());
            mList.add(new TestFragment());
            mList.add(new MineFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
