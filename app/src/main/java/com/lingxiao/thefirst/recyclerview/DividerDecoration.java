package com.lingxiao.thefirst.recyclerview;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;

public class DividerDecoration extends DividerItemDecoration {
    /**
     * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
     * {@link LinearLayoutManager}.
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    public DividerDecoration(Context context, int orientation) {
        super(context, orientation);
    }
}
