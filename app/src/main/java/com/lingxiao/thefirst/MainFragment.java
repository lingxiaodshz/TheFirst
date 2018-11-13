package com.lingxiao.thefirst;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.map.MapCarTranslationActivity;
import com.lingxiao.thefirst.map.MapMarkerActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    public static MainFragment newInsatanc() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        mTvHint.setText(getClass().getSimpleName());
    }

    @OnClick(R.id.tv_hint)
    void onClick(View view) {
        startActivity(new Intent(mContext, MapCarTranslationActivity.class));
    }

//    @Override
//    protected void initFunc() {
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_fragment_main, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.info:
//                startActivity(new Intent(mContext, InfoActivity.class));
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
