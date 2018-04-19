package com.lingxiao.thefirst.selfdefinedview.view06;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lingxiao.thefirst.R;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View06 extends View {
    private Paint mPaint;
    private Path mPath;

    private Bitmap mBitmap;

    public View06(Context context) {
        super(context);
        initView(context);
    }

    public View06(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public View06(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

//        //ColorMatrixColorFilter使用
//        canvas.drawBitmap(mBitmap, null, new Rect(0, 0,
//                500, 500 * mBitmap.getHeight() / mBitmap.getWidth()), mPaint);
//        canvas.translate(510, 0);
//        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
//                1 / 2f, 1 / 2f, 1 / 2f, 0, 0,
//                1 / 3f, 1 / 3f, 1 / 3f, 0, 0,
//                1 / 4f, 1 / 4f, 1 / 4f, 0, 0,
//                0, 0, 0, 1, 0
//        });
//        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
//        canvas.drawBitmap(mBitmap, null, new Rect(0, 0,
//                500, 500 * mBitmap.getHeight() / mBitmap.getWidth()), mPaint);

//        //LightingColorFilter使用
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_blue_pic);
//        int width  = 500;
//        int height = width * mBitmap.getHeight()/mBitmap.getWidth();
//        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);
//        canvas.translate(0, 550);
//        mPaint.setColorFilter(new LightingColorFilter(0x00ff00, 0));
//        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, width, height), mPaint);

//        int width  = 500;
//        int height = width * mBmp.getHeight()/mBmp.getWidth();
//        mPaint.setColor(Color.RED);
//
//        int layerID = canvas.saveLayer(0,0,width,height,mPaint,Canvas.ALL_SAVE_FLAG);
//
//        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);
//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE,100, AvoidXfermode.Mode.TARGET));
//        canvas.drawRect(0,0,width,height,mPaint);
//
//        canvas.restoreToCount(layerID);

        int width = 500;
        int hight = width * mBitmap.getHeight() / mBitmap.getWidth();
        mPaint.setColor(Color.YELLOW);

        int layerID = canvas.saveLayer(0, 0, width, hight, mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBitmap, null, new Rect(0, 0, width, hight), mPaint);
//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE, 100, AvoidXfermode.Mode.TARGET));
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.ADD));
        canvas.drawRect(0, 0, width, hight, mPaint);

        canvas.restoreToCount(layerID);

    }
}
