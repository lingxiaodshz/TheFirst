package com.lingxiao.thefirst;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.base.BaseFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private String[] mTitles = {"首页", "测试", "我的"};

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        hideBackButton();
        // setTitle("MainActivity");

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // setTitle(mTitles[position]);
                setTitleCenter(mTitles[position]);
                mBottomBar.selectTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_main:
                        setPage(0);
                        break;
                    case R.id.tab_test:
                        setPage(1);
                        break;
                    case R.id.tab_mine:
                        setPage(2);
                        break;
                }
            }
        });
    }

    private void setPage(int position) {
        mViewPager.setCurrentItem(position);
        // setTitle(mTitles[position]);
        setTitleCenter(mTitles[position]);
    }

    class MyAdapter extends FragmentPagerAdapter {
        List<BaseFragment> mList = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mList.add(MainFragment.newInsatanc());
            mList.add(TestFragment.newInsatanc());
            mList.add(MineFragment.newInsatanc());
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
