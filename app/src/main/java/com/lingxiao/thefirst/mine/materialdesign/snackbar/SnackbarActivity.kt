package com.lingxiao.thefirst.mine.materialdesign.snackbar

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_snack_bar.*

/**
 * Created by Administrator on 2018/11/6.
 */
class SnackbarActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_snack_bar
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_snakebar.setOnClickListener {
            var snackbar = Snackbar.make(tv_snakebar, "snackbar lala", Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
            snackbar.setAction("取消", object : View.OnClickListener {
                override fun onClick(v: View?) {
                    snackbar.dismiss()
                    showToast("snackbar closed")
                }
            })
            snackbar.setActionTextColor(ContextCompat.getColor(mContext, R.color.colorAccent))
            snackbar.show()
        }
    }
}