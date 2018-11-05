package com.lingxiao.thefirst.mine.bug

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.mine.ClazzBean

/**
 * Created by Administrator on 2018/11/5.
 */
class BugRecordAdapter(context: Context, list: MutableList<ClazzBean>) : RecyclerView.Adapter<BugRecordAdapter.BugRecordHolder>() {
    private var mContext: Context = context
    private var mList: MutableList<ClazzBean> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BugRecordHolder =
            BugRecordHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.activity_bug_record_item, parent, false))

    override fun onBindViewHolder(holder: BugRecordHolder, position: Int) {
        holder.mTvClazz.text = mList[position].desc
        holder.mTvClazz.setOnClickListener {
            var intent = Intent(mContext, mList[position].clazz)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mList.size


    class BugRecordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTvClazz = itemView.findViewById<TextView>(R.id.tv_text)
    }
}