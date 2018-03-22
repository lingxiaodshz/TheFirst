package com.lingxiao.thefirst.selfdefinedview.view01;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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
        paint.setStyle(Paint.Style.STROKE); //默认FILL,FILL填充，FILL_AND_STROKE 填充且描边，STROKE描边
        paint.setStrokeWidth(5);
//        paint.setShadowLayer(10, 25, 25, Color.GREEN);

        //        //设置画布背景颜色
//        canvas.drawRGB(255, 255,255);

        //画圆
//        canvas.drawCircle(200, 200, 150, paint);
        // 画线
//        canvas.drawLine(100, 100, 200, 200, paint);

//        pts:是点的集合，大家下面可以看到，这里不是形成连接线，而是每两个点形成一条直线，
// pts的组织方式为｛x1,y1,x2,y2,x3,y3,……｝

//        float[] pts = {10, 10, 100, 50, 50, 20};//必须是4的倍数，每4个画一条线
//        canvas.drawLines(pts, paint);
//
        //画点
//        canvas.drawPoint(300, 300, paint);
//        canvas.drawPoint(200, 300, paint);

        //画长方形 注意长方形画的不是点位，画的是每条边的位置
        //RectF和Rect的区别主要就是一个是float的参数类型，一个是整形
//        canvas.drawRect(10, 10, 100, 100, paint);
//        RectF rectF = new RectF(120, 10, 250, 120);
//        canvas.drawRect(rectF, paint);
//        Rect rect = new Rect(270, 10, 320, 100);
//        canvas.drawRect(rect, paint);

        //画圆角矩形
//        RectF rectF = new RectF(120, 10, 250, 120);
//        canvas.drawRoundRect(rectF, 10, 50, paint);

        //画椭圆
        //canvas.drawOval(100,100,400,300,paint);

        //画圆弧 true或false用来标记是否画一条横线将圆弧封闭
//        canvas.drawArc(100, 100, 400, 300, 90, 180, false, paint);
//        RectF rectF = new RectF(100,100,400,300);
//        canvas.drawArc(rectF, 0, 90, true, paint);

        //画实心圆弧 false只显示圆弧的弓弦部分实心，true为从圆心到圆弧整个角度范围都是实心
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(100, 100, 400, 300, 45, 90, false, paint);

        //画直线路径
//        Path path = new Path();
//        path.moveTo(10, 10);//设定起始点
//        path.lineTo(100, 200);//第一条直线的终点，也是第二条直线的起点
//        path.lineTo(300, 400);//画第二条直线
//        path.close();//如果不写这行代码不会闭合，写上自动闭合，将起点和终点连接起来
//        canvas.drawPath(path, paint);

        //画矩形路径 如果只画矩形路径顺时针和逆时针没区别，如果沿着路径绘制文字就有区别了
//        Path path = new Path();
//        RectF rectF = new RectF(100, 100, 400, 300);
//        path.addRect(rectF, Path.Direction.CW); //CW clockwise; CCW counter-clockwise
//        canvas.drawPath(path, paint);
//        String text = "落霞与孤鹜齐飞，秋水共长天一色";
//        paint.setColor(Color.GREEN);
//        paint.setTextSize(36);
//        canvas.drawTextOnPath(text, path, 0, 18, paint);

        //画圆角矩形 用float数组时，数组长度必须不小于8
//        Path path = new Path();
//        RectF rectF = new RectF(100, 100, 400, 300);
//        path.addRoundRect(rectF, new float[]{20f, 10f, 20f, 10f, 20f, 10f, 20f, 10f, 20f, 10f}, Path.Direction.CCW);
//        path.addRoundRect(rectF, 10, 20, Path.Direction.CW);
//        canvas.drawPath(path, paint);

        //画圆形路径
//        Path path = new Path();
//        path.addCircle(150, 150, 100, Path.Direction.CW);
//        canvas.drawPath(path, paint);
//        String text = "hello world";
//        paint.setTextSize(30);
//        canvas.drawTextOnPath(text, path, 10, 10, paint);

        //绘制文字
//        paint.setFakeBoldText(true);//设置是否为粗体文字
//        paint.setUnderlineText(true);//设置下划线
//        paint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
//        paint.setStrikeThruText(true);//设置带有删除线效果
//        paint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变
//        paint.setTextSize(36);
//        paint.setStrokeWidth(2);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawText("hello world", 100, 300, paint);

        //绘制文字并指定位置
//        paint.setTextSize(36);
//        paint.setStrokeWidth(2);
//        float[] pos = new float[]{80, 100,
//                80, 200,
//                80, 300,
//                180, 300,
//                80, 400};
//        canvas.drawPosText("hell", pos, paint);//pos的长度可以大于“hell”的长度，反之则不行

        //沿路径绘制文字
//        Path path = new Path();
//        RectF rectF = new RectF(100, 100, 300, 400);
//        path.addRect(rectF, Path.Direction.CW);
//        canvas.drawPath(path, paint);
//        paint.setTextSize(36);
//        paint.setStrokeWidth(2);
//        //hOffset 从其实位置沿着顺时针或者逆时针偏移，vOffset 大于0往内部收缩，小于0往外部扩张
//        canvas.drawTextOnPath("hello world", path, 50, -20, paint);

        //绘制自定义字体
        AssetManager manager = getContext().getAssets();//得到AssetManager
        Typeface typeface = Typeface.createFromAsset(manager, "test.ttf");//根据路径得到Typeface
        paint.setTextSize(72);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        canvas.drawText("你好 世界", 100, 100, paint);

    }
}
