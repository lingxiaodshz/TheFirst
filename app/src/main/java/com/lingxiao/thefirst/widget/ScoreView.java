package com.lingxiao.thefirst.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.lingxiao.thefirst.R;

/**
 * Created by Administrator on 2018/3/15.
 */

public class ScoreView extends View {
    private Paint mPaint;

    public ScoreView(Context context) {
        super(context);
        initView(context);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));

        mBitmap = drawableToBitmap(new BitmapDrawable(BitmapFactory
                .decodeResource(context.getResources(), R.mipmap.ic_launcher_round)));
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private Bitmap mBitmap;
    private int mRadius = 100;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //计算缩放比例
        float mScale = (mRadius * 2.0f) / Math.min(mBitmap.getHeight(), mBitmap.getWidth());

        Matrix matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        bitmapShader.setLocalMatrix(matrix);


        mPaint.setShader(bitmapShader);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, mPaint);
    }
}
