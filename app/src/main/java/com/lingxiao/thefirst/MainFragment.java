package com.lingxiao.thefirst;

import android.content.Intent;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lingxiao.thefirst.base.BaseFragment;
import com.lingxiao.thefirst.map.MapRouteActivity;

import java.util.List;

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
    void onClick1(View view) {
        startActivity(new Intent(mContext, MapRouteActivity.class));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> frags = getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null) {
                    f.onActivityResult(requestCode,resultCode,data);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //handler空闲消息机制的运用，如果返回true会一直被调用，返回false只执行一次，然后从空闲列表移出
//        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
//            @Override
//            public boolean queueIdle() {
//                Log.i(TAG, "queueIdle: ");
//                showToast("queueIdle");
//                return false;
//            }
//        });
    }
}
