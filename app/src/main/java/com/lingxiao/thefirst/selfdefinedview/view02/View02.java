package com.lingxiao.thefirst.selfdefinedview.view02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/23.
 */

public class View02 extends View {
    private Paint paint;

    public View02(Context context) {
        super(context);
        init(context);
    }

    public View02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public View02(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制矩形区域
//        Region rgn = new Region(10,10,100,100);

        //消除以前的，重新绘制
//        rgn.set(100, 100, 200, 200);
//        drawRegion(canvas, rgn, paint);

        //构造一个椭圆路径
//        Path ovalPath = new Path();
//        RectF rect = new RectF(50, 50, 200, 500);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(rect, paint);
//        paint.setStyle(Paint.Style.FILL);
//        ovalPath.addOval(rect, Path.Direction.CCW);
////        ovalPath.addArc(rect, 0, 90);
//        //SetPath时,传入一个比椭圆区域小的矩形区域,让其取交集
//        Region rgn = new Region();
//        rgn.setPath(ovalPath, new Region(50, 50, 200, 400));
//        drawRegion(canvas, rgn, paint);

    }

    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();

        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }
}
