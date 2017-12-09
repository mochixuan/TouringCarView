package com.wx.touringcarlayoutview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangxuan on 2017/12/8.
 */

public class BesselBgView extends View{

    private Paint mPaint;
    private float mHeightScale ;
    private int mBesselColor1;
    private int mBesselColor2;
    private int[] mBackgroundColors;
    private boolean isOpenBessel;

    public BesselBgView(Context context) {
        this(context,null);
    }

    public BesselBgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BesselBgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void initBesselBgView(float besselHeightScale,int besselColor1,int besselColor2,boolean isOpenBessel) {
        mHeightScale = besselHeightScale;
        mBesselColor1 = besselColor1;
        mBesselColor2 = besselColor2;
        this.isOpenBessel = isOpenBessel;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBackgroundColors != null) {
            LinearGradient gradient = new LinearGradient(
                    0,0,
                    0,getMeasuredHeight(),
                    mBackgroundColors,
                    null,
                    Shader.TileMode.CLAMP
            );
            mPaint.setShader(gradient);
            canvas.drawRect(0f,0f,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        }

        if (isOpenBessel) {
            Path path = new Path();
            LinearGradient gradient1 = new LinearGradient(
                    0,getMeasuredHeight()*(1-mHeightScale*1.2f),
                    0,getMeasuredHeight()*1.2f,
                    mBesselColor2,
                    mBesselColor1,
                    Shader.TileMode.CLAMP
            );
            mPaint.setShader(gradient1);

            path.reset();
            path.moveTo(0,(1-mHeightScale*0.66f)*getMeasuredHeight());
            path.cubicTo(
                    getMeasuredWidth()*0.2f,
                    (1-mHeightScale)*getMeasuredHeight(),
                    getMeasuredWidth()*0.5f,
                    (1-mHeightScale*0.4f)*getMeasuredHeight(),
                    getMeasuredWidth()*0.6f,
                    getMeasuredHeight()
            );
            path.lineTo(0,getMeasuredHeight());
            path.close();
            canvas.drawPath(path,mPaint);

            LinearGradient gradient2 = new LinearGradient(
                    0,getMeasuredHeight()*(1-mHeightScale*1.2f),
                    0,getMeasuredHeight()*1.2f,
                    mBesselColor1,
                    mBesselColor2,
                    Shader.TileMode.CLAMP
            );
            mPaint.setShader(gradient2);

            path.reset();
            path.moveTo(0,(1-mHeightScale*0.2f)*getMeasuredHeight());
            path.cubicTo(
                    getMeasuredWidth()*0.25f,
                    (1-mHeightScale*0.4f)*getMeasuredHeight(),
                    getMeasuredWidth()*0.35f,
                    (1-mHeightScale)*getMeasuredHeight(),
                    getMeasuredWidth()*0.5f,
                    (1-mHeightScale)*getMeasuredHeight()
            );
            path.cubicTo(
                    getMeasuredWidth()*0.65f,
                    (1-mHeightScale)*getMeasuredHeight(),
                    getMeasuredWidth()*0.75f,
                    (1-mHeightScale*0.15f)*getMeasuredHeight(),
                    getMeasuredWidth(),
                    (1-mHeightScale*0.25f)*getMeasuredHeight()
            );
            path.lineTo(getMeasuredWidth(),getMeasuredHeight());
            path.lineTo(0,getMeasuredHeight());
            path.close();
            canvas.drawPath(path,mPaint);

            LinearGradient gradient3 = new LinearGradient(
                    0,getMeasuredHeight()*(1-mHeightScale*1.2f),
                    0,getMeasuredHeight()*1.2f,
                    mBesselColor2,
                    mBesselColor1,
                    Shader.TileMode.CLAMP
            );
            mPaint.setShader(gradient3);

            path.reset();
            path.moveTo(0,(1-mHeightScale*0.38f)*getMeasuredHeight());
            path.cubicTo(
                    getMeasuredWidth()*0.25f,
                    getMeasuredHeight(),
                    getMeasuredWidth()*0.58f,
                    (1-mHeightScale*0.6f)*getMeasuredHeight(),
                    getMeasuredWidth()*0.68f,
                    (1-mHeightScale*0.7f)*getMeasuredHeight()
            );
            path.cubicTo(
                    getMeasuredWidth()*0.76f,
                    (1-mHeightScale*0.92f)*getMeasuredHeight(),
                    getMeasuredWidth()*0.86f,
                    (1-mHeightScale)*getMeasuredHeight(),
                    getMeasuredWidth(),
                    (1-mHeightScale*0.66f)*getMeasuredHeight()
            );
            path.lineTo(getMeasuredWidth(),getMeasuredHeight());
            path.lineTo(0,getMeasuredHeight());
            path.close();
            canvas.drawPath(path,mPaint);
        }

    }

    public void setBesselColor1(int besselColor1) {
        this.mBesselColor1 = besselColor1;
        invalidate();
    }

    public void setBesselColor2(int besselColor2) {
        this.mBesselColor2 = besselColor2;
        invalidate();
    }

    public void setBackgroundColors(int[] backgroundColors) {
        this.mBackgroundColors = backgroundColors;
        invalidate();
    }
}
