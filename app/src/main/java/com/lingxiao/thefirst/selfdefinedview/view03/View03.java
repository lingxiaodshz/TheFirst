package com.lingxiao.thefirst.selfdefinedview.view03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View03 extends View {
    private Paint paint;
    public View03(Context context) {
        super(context);
        initView(context);
    }

    public View03(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public View03(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画布平移
//        RectF rectF = new RectF(100, 100, 400, 200);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(rectF, paint);
//        //translate会将（0,0）坐标平移
//        canvas.translate(0, 300);
//        paint.setColor(Color.GREEN);
//        canvas.rotate(30);
//        canvas.drawRect(rectF, paint);

        //画布旋转 注意是canvas旋转而不是图旋转了
//        RectF rectF = new RectF(100, 100, 400, 200);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(rectF, paint);
//        paint.setColor(Color.GREEN);
//        canvas.rotate(30);
//        canvas.drawRect(rectF, paint);

        //画布缩放
//        RectF rectF = new RectF(100, 100, 400, 200);
//        canvas.drawRect(rectF, paint);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.GREEN);
//        //小数代表坐标缩小，大于1则代表坐标放大
////        canvas.scale(0.5f, 3);
//        //px,py表示缩放之后的起始画图点
//        canvas.scale(0.5f, 3, 100, 100);
//        canvas.drawRect(rectF, paint);

        //裁剪画布canvas
//        canvas.drawColor(Color.parseColor("#123456"));
//        Path path = new Path();
//        path.addOval(new RectF(100, 100, 400, 300), Path.Direction.CW);
//        canvas.clipPath(path);
//        canvas.drawColor(Color.RED);

        //画布的保存与恢复
        canvas.drawColor(Color.parseColor("#123456"));
        canvas.save();//保存场景
        Path path = new Path();
        path.addCircle(200, 200, 100, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawColor(Color.RED);
        canvas.restore();//恢复场景
        canvas.drawCircle(400, 400, 300, paint);
    }
}
