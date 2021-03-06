package com.lingxiao.thefirst;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.utils.PackageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static TestFragment newInsatanc() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView(View view) {
        mTvHint.setText(getClass().getSimpleName());

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));

        AppAdapter adapter = new AppAdapter(mContext, PackageUtil.getResolveInfos(mContext));
        mRecyclerView.setAdapter(adapter);
    }

    static class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppHolder> {

        private List<ResolveInfo> mResolveInfos;
        private Context mContext;

        public AppAdapter(Context context, List<ResolveInfo> resolveInfos) {
            this.mContext = context;
            this.mResolveInfos = resolveInfos;
        }

        @Override
        public AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.gridview_item_app, parent, false);
            return new AppHolder(view);
        }

        @Override
        public void onBindViewHolder(AppHolder holder, int position) {
            final ResolveInfo resolveInfo = mResolveInfos.get(position);

            holder.mIvIcon.setImageDrawable(PackageUtil.getAppIcon(mContext, resolveInfo));
            holder.mTvName.setText(PackageUtil.getAppName(mContext, resolveInfo));

            //打开app
            holder.mIvIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PackageUtil.startActivity(mContext, resolveInfo);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mResolveInfos == null ? 0 : mResolveInfos.size();
        }

        static class AppHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_icon)
            ImageView mIvIcon;
            @BindView(R.id.iv_delete)
            ImageView mIvDelete;
            @BindView(R.id.tv_name)
            TextView mTvName;

            public AppHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }


}
