package com.lingxiao.thefirst.mine.nestedscroll

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lingxiao.thefirst.R

class NestedAdapter(context: Context, list: MutableList<String>) : RecyclerView.Adapter<NestedAdapter.NestedHolder>() {
    private var mContext: Context = context
    private var mList: MutableList<String> = list

    override fun getItemCount(): Int {
        if (mList == null) {
            return 0
        }
        return mList.size
    }

    override fun onBindViewHolder(holder: NestedHolder, position: Int) {
        holder.mTvClazz.text = mList[position]+" RecyclerView $position"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedHolder {
        var view = View.inflate(mContext, R.layout.fragment_mine_item, null)
        return NestedHolder(view)
    }

    class NestedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTvClazz = itemView.findViewById<TextView>(R.id.tv_clazz)
    }
}