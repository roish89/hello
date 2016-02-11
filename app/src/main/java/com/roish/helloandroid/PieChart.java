package com.roish.helloandroid;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by roish on 08/02/2016.
 */
public class PieChart extends View {


    private Paint mPiePaint;
    private Paint mShadowPaint;
    private Paint mTextPaint;
    private float mTextHeight;


    public PieChart(Context context) {
        super(context);
        init();
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       // mTextPaint.setColor(mTextColor);
        if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);
        }

        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPiePaint.setColor(Color.BLACK);
        mPiePaint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, mPiePaint);
    }

}
