package com.lingxiao.thefirst.selfdefinedview.view04;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.lingxiao.thefirst.R;
import com.lingxiao.thefirst.utils.DensityUtil;

/**
 * Created by Administrator on 2018/3/26.
 */

public class View04 extends View {
    private Paint paint;
    private Path mPath;

    public View04(Context context) {
        super(context);
        initView(context);
    }

    public View04(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public View04(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

//        BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher);
//        final Bitmap bmm = bd.getBitmap();

//        //创建bitmap工厂的配置参数
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        //返回null，不去真正解析位图，只是得到宽高等信息
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getResources(), R.drawable.ic_big_pic, options);
//        int imgWidth = options.outWidth;
//        int imgHeight = options.outHeight;
//        Log.e(getClass().getSimpleName(), "图片宽" + imgWidth + "图片高" + imgHeight);
//
//        // 计算缩放比
//        int scale = 1;
//        int scalex = imgWidth / 200;
//        int scaley = imgHeight / 200;
//        scale = scalex > scaley ? scalex : scaley;
//        //按照缩放比显示图片
//        Log.e(getClass().getSimpleName(), "++++++++++++" + String.valueOf(scale));
//
//        options.inSampleSize = scale;
//        //开始真正解析位图
//        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_big_pic, options);


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_big_pic);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //第二个参数是将bitmap中的这个位置截取，如果想显示整个bitmap需要设置为null
        canvas.drawBitmap(bitmap, null,
                new Rect(100, 100, 200, 200), paint);

    }
}
