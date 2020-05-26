package com.lingxiao.thefirst.mine.materialdesign.textinputlayout

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.text.TextUtils
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_text_input_layout.*

/**
 * Created by Administrator on 2018/11/7.
 */
class TextInputLayoutActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_text_input_layout
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_login.setOnClickListener {
            if (TextUtils.isEmpty(tie_phone.text)) {
                showError(til_phone, "手机号不能为空")
            } else {
                if (tie_phone.text!!.trim().length < 11) {
                    showError(til_phone, "手机号输入有误")
                } else {
                    til_phone.isErrorEnabled = false
                }
            }
            if (TextUtils.isEmpty(tie_password.text)) {
                showError(til_password, "密码不能为空")
            } else {
                if (tie_password.text!!.trim().length < 6) {
                    showError(til_password, "密码不能少于6位")
                } else {
                    til_password.isErrorEnabled = false
                }
            }
        }
    }

    private fun showError(textInputLayout: TextInputLayout, errorStr: String) {
        textInputLayout.error = errorStr
        var editText = textInputLayout.editText
        if (editText != null) {
            editText.isFocusable = true
            editText.isFocusableInTouchMode = true
            editText.requestFocus()
        }
    }
}