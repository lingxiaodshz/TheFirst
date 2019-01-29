package com.lingxiao.thefirst.mine.materialdesign.autoaszeabletextview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.util.TypedValue
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_auto_sizeable_text_view.*

/**
 * @author wxh
 * @date   2019/1/29
 */

class AutoSizeableTextViewActivity : BaseActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_auto_sizeable_text_view

    @SuppressLint("SetTextI18n")
    override fun initView(savedInstanceState: Bundle?) {
        text1.text = "夜饮东坡醒复醉，归来仿佛三更。家童鼻息已雷鸣。敲门都不应，倚杖听江声。"

        text2.text = "夜饮东坡醒复醉，归来仿佛三更。家童鼻息已雷鸣。敲门都不应，倚杖听江声。\n" +
                "长恨此身非我有，何时忘却营营。夜阑风静縠纹平。小舟从此逝，江海寄余生。"


        TextViewCompat.setAutoSizeTextTypeWithDefaults(text1, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(text1, 8, 25, 1, TypedValue.COMPLEX_UNIT_DIP);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(text2, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(text2, 8, 25, 1, TypedValue.COMPLEX_UNIT_DIP);
    }

}