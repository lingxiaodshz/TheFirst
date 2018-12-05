package com.lingxiao.thefirst.mine.bottomsheet

import android.os.Bundle
import android.view.View
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_bottom_sheet
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_bottom_sheet.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showToast("BottomSheet")
            }
        })
        tv_bottom_sheet_dialog.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showToast("BottomSheetDialog")
            }
        })
        tv_bottom_sheet_dialog_fragment.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                showToast("BottomSheetDialogFragment")
            }
        })
    }

}