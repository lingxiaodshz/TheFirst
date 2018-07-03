package com.lingxiao.thefirst;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.test.InfoActivity;

import butterknife.BindView;

public class MineFragment extends BaseFragment {
    @BindView(R.id.tv_hint)
    TextView mTvHint;

    public static MineFragment newInsatanc() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.fragment_main;
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
        mTvHint.setText(getClass().getSimpleName());
    }
}
