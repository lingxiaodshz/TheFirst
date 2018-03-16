package com.lingxiao.thefirst.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/1/23.
 */

@SuppressLint("AppCompatCustomView")
public class LastInputEditText extends EditText {

    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            if (getText().length() > 1) {
                if (selStart < 1) {
                    setSelection(1);
                }
//                else{
//                    setSelection(selStart, selEnd);
//                }
            }
        }

    }
}
