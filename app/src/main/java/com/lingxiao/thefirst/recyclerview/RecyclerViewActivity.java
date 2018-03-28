package com.lingxiao.thefirst.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

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
