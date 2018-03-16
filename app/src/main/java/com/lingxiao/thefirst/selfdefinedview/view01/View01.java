package com.lingxiao.thefirst.selfdefinedview.view01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Administrator on 2018/3/16.
 */

//下面先说下Paint的基本设置函数：
//
//        paint.setAntiAlias(true);//抗锯齿功能
//        paint.setColor(Color.RED);  //设置画笔颜色
//        paint.setStyle(Style.FILL);//设置填充样式
//        paint.setStrokeWidth(30);//设置画笔宽度
//        paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
//        前两个没什么好说的，看填充样式的区别：
//
//        1、void setStyle (Paint.Style style)     设置填充样式
//
//        Paint.Style.FILL    :填充内部
//        Paint.Style.FILL_AND_STROKE  ：填充内部和描边
//        Paint.Style.STROKE  ：仅描边

public class View01 extends View {

    public View01(Context context) {
        super(context);
    }

    public View01(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public View01(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL); //默认FILL,FILL填充，FILL_AND_STROKE 填充且描边，STROKE描边
        paint.setStrokeWidth(5);
        paint.setShadowLayer(10, 25, 25, Color.GREEN);

        //        //设置画布背景颜色
//        canvas.drawRGB(255, 255,255);

        canvas.drawCircle(200, 200, 150, paint);

    }
}
