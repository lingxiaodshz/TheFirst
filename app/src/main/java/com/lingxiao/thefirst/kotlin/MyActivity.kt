package com.lingxiao.thefirst.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var textView = TextView(this)
        textView.setOnClickListener { Toast.makeText(this@MyActivity, "111", Toast.LENGTH_SHORT).show() }
    }
}
