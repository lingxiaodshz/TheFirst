package com.lingxiao.thefirst.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingxiao.thefirst.R;


/**
 * 输入EditText的内容为¥123这种模式，且光标不能移动到¥的左侧
 * Created by Administrator on 2018/3/16.
 */

public class LastInputActivity extends Activity {
    private LastInputEditText mEtInput;
    private EditText mEtInput1;
    private TextView mTvMoney;
    private LinearLayout mLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_input);

        mEtInput = findViewById(R.id.et_input);
        mEtInput1 = findViewById(R.id.et_input1);
        mTvMoney = findViewById(R.id.tv_money);
        mLayout = findViewById(R.id.ll_layout);

        mEtInput.addTextChangedListener(mInputWatcher);
        mEtInput1.addTextChangedListener(mWatcher);
    }

    private TextWatcher mInputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (TextUtils.equals(mEtInput.getText(), "0")) {
                mEtInput.setText("");
                return;
            }
            mEtInput.removeTextChangedListener(mInputWatcher);
            StringBuilder sb = new StringBuilder();
            boolean flag = false; //用于标记是否是第一次输入
            if (!mEtInput.getText().toString().startsWith("¥")) {
                sb.append("¥");
                flag = true;
            }

            sb.append(mEtInput.getText());
            int index = mEtInput.getSelectionStart();
            if (sb.length() == 1) {
                mEtInput.setText("");
            } else {
                mEtInput.setText(sb);
                if (flag) {
                    mEtInput.setSelection(index + 1);
                } else {
                    mEtInput.setSelection(index);
                }
            }
            mEtInput.addTextChangedListener(mInputWatcher);
        }
    };

    private TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(mEtInput1.getText())) {
                mTvMoney.setVisibility(View.VISIBLE);
            } else {
                mTvMoney.setVisibility(View.GONE);
            }
        }
    };
}
