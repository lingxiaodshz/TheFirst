package com.lingxiao.thefirst.mine.fileprovider

import android.os.Bundle
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity

class FileProviderActivity : BaseActivity() {
    override fun getLayoutResource(): Int {
        return R.layout.activity_file_provider
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTitle("FileProvider")

    }


}