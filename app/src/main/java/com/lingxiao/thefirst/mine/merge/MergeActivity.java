package com.lingxiao.thefirst.mine.merge;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

public class MergeActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.activity_merge;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("Merge标签试用");
        /**
         * merge标签内的文件可以根据include所在的父布局进行设置，但是如果是不同类型的父布局可能会显示不同效果
         * LinearLayout和RelativeLayout显示肯定就会不同
         */
    }
}
