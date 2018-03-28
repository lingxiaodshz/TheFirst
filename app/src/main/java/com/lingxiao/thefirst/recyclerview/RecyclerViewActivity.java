package com.lingxiao.thefirst.recyclerview;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.recyclerview.adapter.FirstAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView mRecyclerView;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_recycler_view;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("RecyclerView");
        mRecyclerView = findViewById(R.id.recycler_view);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //注意decoration的divider需要在theme里面去设置，并且不能用color
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new FirstAdapter(this, getData()));
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<20;i++) {
            list.add("测试条目" + i);
        }
        return list;
    }
}
