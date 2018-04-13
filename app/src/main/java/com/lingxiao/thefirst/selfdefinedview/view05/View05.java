package com.lingxiao.thefirst.selfdefinedview.view05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lingxiao.thefirst.R;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View05 extends View {
    private Paint mPaint;
    private Path mPath;

    private Bitmap mBitmap;

    public View05(Context context) {
        super(context);
        initView(context);
    }

    public View05(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public View05(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setARGB(255, 200, 100, 100);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);
//        mPaint.setColor(Color.BLUE);

        mPath = new Path();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_dog);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        //注意：使用ColorMatrix时不能使用setColor给画笔设置颜色值，必须采用ARGB的方式
//        // 生成色彩矩阵
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                0, 0, 0, 0, 0,
//                0, 1, 0, 0, 0,
//                0, 0, 1, 0, 0,
//                0, 0, 0, 0.5f, 0,
//        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//
//        canvas.drawRect(0, 0, 300, 300, mPaint);

        //绘制原始位图
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, 500, 500 * mBitmap.getHeight() / mBitmap.getWidth()), mPaint);
        canvas.translate(510, 0);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0.5f, 0,
        });
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, 500, 500 * mBitmap.getHeight() / mBitmap.getWidth()), mPaint);

    }
}
