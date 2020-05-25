package com.lingxiao.thefirst.mine.viewstub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ViewStubActivity extends BaseActivity {
    @BindView(R.id.view_stub)
    ViewStub mViewStub;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_view_stub;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("ViewStub标签");

        /**
         * ViewStub中的layout不能包括merge标签，否则会报下面的错误
         * <merge /> can be used only with a valid ViewGroup root and attachToRoot=true
         */
    }

    @OnClick(R.id.tv_test)
    void onClick1(View view) {
        switch (view.getId()) {
            case R.id.tv_test:
                // 注意：此处ViewStub在没加载的时候是gone状态，家在之后仍然是gone，而不是visible
                // 方法一：ViewStub被inflate后就不会再布局中存在。所以每次在inflate的时候重新findViewById去页面中寻找一下ViewStub，
                // 如果返回值不为null则ViewStub没有被inflate过。
//                ViewStub viewStub = (ViewStub) findViewById(R.id.view_stub);
//                if (viewStub != null) {
//                    viewStub.inflate();
//                }
                // 方法二：不必每次都初始化，利用ViewStub的parent来判断。当ViewStub被inflate后，getParent返回值是null
                if (mViewStub.getParent() != null) {
                    mViewStub.inflate();
                }
                break;
                // 总结：以上两种方式都有效，第二种方式更好一些
        }
    }
}
