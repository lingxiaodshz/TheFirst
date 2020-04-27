package com.lingxiao.thefirst.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author luckw
 * @date 2020/4/27
 */
public class SaveRestoreView extends View {
    public SaveRestoreView(Context context) {
        super(context);
    }

    public SaveRestoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveRestoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint backgroundPaint = new Paint();
    private Paint linePaint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int px = getMeasuredWidth();

        int py = getMeasuredWidth();

        backgroundPaint.setColor(getResources().getColor(android.R.color.background_light));
        canvas.drawRect(0, 0, px, py, backgroundPaint);
        canvas.save();
        canvas.rotate(90, px/2, py/2);
        canvas.save();
        canvas.translate(px / 4, py / 4);

        // Draw up arrow
        linePaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
        canvas.drawLine(px / 2, 0, 0, py / 2, linePaint);
        canvas.drawLine(px / 2, 0, px, py / 2, linePaint);
        canvas.drawLine(px / 2, 0, px / 2, py, linePaint);
        canvas.restore();
        canvas.restore();

        // Draw circle
        canvas.drawCircle(px - 10, py - 10, 10, linePaint);
    }
}
