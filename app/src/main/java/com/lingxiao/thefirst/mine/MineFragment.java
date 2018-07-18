package com.lingxiao.thefirst.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.constraint.ConstraintLayoutActivity;
import com.lingxiao.thefirst.mine.okhttp.OkHttpTestActivity;
import com.lingxiao.thefirst.mine.retrofit.RetrofitTestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<ClazzBean> mList = new ArrayList<>();

    public static MineFragment newInsatanc() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_mine;
    }

//    @Override
//    protected void initFunc() {
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_fragment_mine, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.setting:
//                Toast.makeText(mContext, "setting", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.main:
//                Toast.makeText(mContext, "setting", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        initData();
        mRecyclerView.setAdapter(new ClazzAdapter(mContext, mList));
    }

    private void initData() {
        ClazzBean bean01 = new ClazzBean();
        bean01.desc = "OKHttpTest";
        bean01.clazz = OkHttpTestActivity.class;
        mList.add(bean01);

        ClazzBean bean02 = new ClazzBean();
        bean02.desc = "RetrofitTest";
        bean02.clazz = RetrofitTestActivity.class;
        mList.add(bean02);

        ClazzBean bean03 = new ClazzBean();
        bean03.desc = "ConstraintLayout";
        bean03.clazz = ConstraintLayoutActivity.class;
        mList.add(bean03);
    }

    static class ClazzAdapter extends RecyclerView.Adapter<ClazzAdapter.ClazzHolder> {
        private Context mContext;
        private List<ClazzBean> mList;

        public ClazzAdapter(Context context, List<ClazzBean> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public ClazzHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_mine_item, parent, false);
            return new ClazzHolder(view);
        }

        @Override
        public void onBindViewHolder(ClazzHolder holder, int position) {
            final ClazzBean clazzBean = mList.get(position);

            holder.mTvClazz.setText(clazzBean.desc);
            holder.mTvClazz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, clazzBean.clazz);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        static class ClazzHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_clazz)
            TextView mTvClazz;

            public ClazzHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

}
