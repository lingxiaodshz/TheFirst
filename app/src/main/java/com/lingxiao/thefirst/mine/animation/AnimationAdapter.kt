package com.lingxiao.thefirst.mine.animation

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.mine.ClazzBean

class AnimationAdapter(context: Context, list: MutableList<ClazzBean>) : RecyclerView.Adapter<AnimationAdapter.AnimationHolder>() {
    var mContext = context
    var mList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimationHolder =
            AnimationHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.activity_animation_item, parent, false))

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: AnimationHolder, position: Int) {
        holder.mTvClazz.text = mList[position].desc
        holder.mTvClazz.setOnClickListener {
            var intent = Intent(mContext, mList[position].clazz)
            mContext.startActivity(intent)
        }
    }

    class AnimationHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTvClazz = itemView.findViewById<TextView>(R.id.tv_clazz)
    }
}