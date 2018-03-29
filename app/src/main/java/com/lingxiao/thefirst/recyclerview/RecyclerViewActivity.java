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
import android.text.TextUtils;
import android.widget.Toast;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;
import com.lingxiao.thefirst.recyclerview.adapter.FirstAdapter;

import java.util.ArrayList;
import java.util.Iterator;
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
        decoration.setDrawable(getResources().getDrawable(R.drawable.recycler_item_divider_margin));
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new FirstAdapter(this, getData()));

//        List<String> list = new ArrayList<>();
//        list.add("abc1");
//        list.add("abc2");
//        list.add("abc2");
//        list.add("abc4");
//        for (int i = list.size() - 1; i >= 0; i--) {
//            if (TextUtils.equals(list.get(i), "abc2")) {
//                list.remove(i);
//            }
//        }
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            if (TextUtils.equals(iterator.next(), "abc2")) {
//                iterator.remove();
//            }
//        }
//        Toast.makeText(this, list.size() + "", Toast.LENGTH_SHORT).show();
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试条目" + i);
        }
        return list;
    }
}
