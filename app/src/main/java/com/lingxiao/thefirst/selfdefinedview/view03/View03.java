package com.lingxiao.thefirst.selfdefinedview.view03;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View03 extends View {
    private Paint paint;
    private Path mPath;
    private int dx;
    private int mItemWaveLength = 400;
    private  int originY = 300;

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

        mPath = new Path();
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
//        canvas.drawColor(Color.parseColor("#123456"));
//        canvas.save();//保存场景
//        Path path = new Path();
//        path.addCircle(200, 200, 100, Path.Direction.CW);
//        canvas.clipPath(path);
//        canvas.drawColor(Color.RED);
//        canvas.restore();//恢复场景
//        canvas.drawCircle(400, 400, 300, paint);

        //画基线，写文字 注意drawText中的y代表的事基线的位置
//        canvas.drawLine(0, 200, 1000, 200, paint);
//        paint.setTextSize(120);
//        paint.setColor(Color.RED);
//        canvas.drawText("heplg 你好", 100, 200, paint);

//        Android给我们提供了一个类：FontMetrics，它里面有四个成员变量：
//        ascent: 系统建议的，绘制单个字符时，字符应当的最高高度所在线
//        descent:系统建议的，绘制单个字符时，字符应当的最低高度所在线
//        top: 可绘制的最高高度所在线
//        bottom: 可绘制的最低高度所在线

        //绘制贝塞尔曲线
//        Path path = new Path();
//        path.moveTo(100, 300);
//        path.quadTo(200, 250, 300, 400);
//        path.quadTo(450, 500, 550, 300);
//        canvas.drawPath(path, paint);

        //绘制手势轨迹 需要onTouch
//        canvas.drawPath(mPath, paint);

        //绘制波浪
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPath.reset();
        int halfWaveLen = mItemWaveLength / 2;
        mPath.moveTo(-mItemWaveLength + dx, originY);
        for (int i = -mItemWaveLength; i <= getWidth() + mItemWaveLength; i += mItemWaveLength) {
            mPath.rQuadTo(halfWaveLen / 2, -100, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, 100, halfWaveLen, 0);
        }
        mPath.lineTo(getWidth(), getHeight());
        mPath.lineTo(0, getHeight());
        mPath.close();

        canvas.drawPath(mPath, paint);

    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                originY += 1;
                postInvalidate();
            }
        });
        animator.start();
    }

//    float mX, mY;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mX = event.getX();
//                mY = event.getY();
//                mPath.moveTo(mX, mY);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float x = (mX + event.getX()) / 2;
//                float y = (mY + event.getY()) / 2;
//                //采用贝塞尔曲线方式，看起来更平滑
//                mPath.quadTo(mX, mY, x, y);
//                mX = event.getX();
//                mY = event.getY();
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
