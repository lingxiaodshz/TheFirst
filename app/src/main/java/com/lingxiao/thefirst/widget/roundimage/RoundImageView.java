package com.lingxiao.thefirst.widget.roundimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class RoundImageView extends ImageView {
    private static final Xfermode mXfermode
            = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    private Bitmap mBitmap;
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakReference;

    public RoundImageView(Context context) {
        super(context);
        init(context);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RoundImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void invalidate() {
        mWeakReference = null;
        if (mBitmap != null) {
            mBitmap.recycle();
        }
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int layerIndex = canvas.saveLayer(0, 0, getWidth(), getHeight(),
                    null, Canvas.ALL_SAVE_FLAG);

            try {
                Bitmap bitmap = mWeakReference != null ? mWeakReference.get() : null;
                if (bitmap == null || bitmap.isRecycled()) {
                    Drawable drawable = getDrawable();
                    if (drawable != null) {
                        bitmap = Bitmap.createBitmap(getWidth(),
                                getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas bitmapCanvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(bitmapCanvas);

                        if (mBitmap == null || mBitmap.isRecycled()) {
                            mBitmap = getBitmap();
                        }

                        mPaint.reset();
                        mPaint.setFilterBitmap(false);
                        mPaint.setXfermode(mXfermode);

                        bitmapCanvas.drawBitmap(mBitmap, 0, 0, mPaint);

                        mWeakReference = new WeakReference<>(bitmap);
                    }
                }

                if (bitmap != null) {
                    mPaint.setXfermode(null);
                    canvas.drawBitmap(bitmap, 0, 0, mPaint);
                    return;
                }
            } catch (Exception e) {
                System.gc();
            } finally {
                canvas.restoreToCount(layerIndex);
            }

        } else {
            super.onDraw(canvas);
        }


    }

    private Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        canvas.drawOval(new RectF(0, 0, getWidth(), getHeight()), paint);
        return bitmap;
    }

}
