package com.lingxiao.thefirst.mine.materialdesign

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
 * Created by Administrator on 2018/11/6.
 */
class MaterialDesignAdapter(context: Context, list: MutableList<ClazzBean>)
    : RecyclerView.Adapter<MaterialDesignAdapter.MaterialDesignHolder>() {
    var mContext = context
    var mList = list

    override fun onBindViewHolder(holder: MaterialDesignHolder, position: Int) {
        var bean = mList[position]
        holder.tvClazz.text = bean.desc
        holder.tvClazz.setOnClickListener {
            var intent = Intent(mContext, bean.clazz)
            mContext.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialDesignHolder =
            MaterialDesignHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.activity_material_design_item, parent, false))

    override fun getItemCount(): Int = mList.size

    class MaterialDesignHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvClazz = itemView.findViewById<TextView>(R.id.tv_clazz)
    }
}