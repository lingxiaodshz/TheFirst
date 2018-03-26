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

        //绘制区域组合
//        假设用region1  去组合region2
//        public enum Op {
//            DIFFERENCE(0), //最终区域为region1 与 region2不同的区域
//            INTERSECT(1), // 最终区域为region1 与 region2相交的区域
//            UNION(2),      //最终区域为region1 与 region2组合一起的区域
//            XOR(3),        //最终区域为region1 与 region2相交之外的区域
//            REVERSE_DIFFERENCE(4), //最终区域为region2 与 region1不同的区域
//            REPLACE(5); //最终区域为为region2的区域
//        }
        Rect rect1 = new Rect(200, 100, 300, 400);
        Rect rect2 = new Rect(100, 200, 400, 300);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);

        region1.op(region2, Region.Op.UNION);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas, region1, paint);
    }

    private void drawRegion(Canvas canvas, Region rgn, Paint paint) {
        RegionIterator iter = new RegionIterator(rgn);
        Rect r = new Rect();

        while (iter.next(r)) {
            canvas.drawRect(r, paint);
        }
    }
}
