package com.lingxiao.thefirst.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingxiao.thefirst.R;

import java.util.List;

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstHolder> {

    private Context mContext;
    private List<String> mItems;

    public FirstAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mItems = list;
    }

    @Override
    public FirstHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FirstHolder(View.inflate(mContext, R.layout.activity_recycler_view_first_item, null));
    }

    @Override
    public void onBindViewHolder(FirstHolder holder, int position) {
        holder.tvItem.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class FirstHolder extends RecyclerView.ViewHolder {
        public FirstHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
        }

        TextView tvItem;

    }
}
