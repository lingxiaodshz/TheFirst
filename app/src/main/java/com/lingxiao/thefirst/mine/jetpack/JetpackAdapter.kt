package com.lingxiao.thefirst.mine.jetpack

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
 * @author luckw
 * @date   2020/5/25
 */

class JetpackAdapter : RecyclerView.Adapter<JetpackAdapter.JetpackHolder> {

    class JetpackHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTvClazz: TextView = itemView.findViewById(R.id.tv_clazz)
    }

    var mContext: Context
    var mList: List<ClazzBean>
    var mInflater: LayoutInflater

    constructor(context: Context, list: List<ClazzBean>) {
        mInflater = LayoutInflater.from(context)
        mContext = context
        mList = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JetpackHolder {
        return JetpackHolder(mInflater.inflate(R.layout.fragment_mine_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: JetpackHolder, position: Int) {
        var clazzBean = mList.get(position)
        holder.mTvClazz.text = clazzBean.desc
        holder.mTvClazz.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var intent = Intent(mContext, clazzBean.clazz)
                mContext.startActivity(intent)
            }
        })
    }
}