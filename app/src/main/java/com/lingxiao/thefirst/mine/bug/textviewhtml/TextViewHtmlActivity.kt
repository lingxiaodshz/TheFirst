package com.lingxiao.thefirst.mine.bug.textviewhtml

import android.os.Bundle
import android.text.Html
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import com.lingxiao.thefirst.utils.StringUtil
import kotlinx.android.synthetic.main.activity_textview_html.*

/**
 * Created by Administrator on 2018/11/5.
 */
class TextViewHtmlActivity : BaseActivity() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_textview_html
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_text.text = StringUtil.createJsonDataSpan(getString(R.string.rich_text), mContext)

        // 字符串中如果有连续空格，如果用Html.fromHtml方法，会将空格变成一个，
        // 若仍想显示多个空格，需要将空格变成&nbsp;
        tv_html_text.text = StringUtil.parseColorString(getString(R.string.html_text).replace(" ","&nbsp;"))
    }
}