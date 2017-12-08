package com.wx.touringcarlayoutview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by wangxuan on 2017/12/7.
 */

public class RingView extends View {

    private Paint mPaint;

    private float FirstStageScale ;
    private float SecondStageScale ;

    private float mCurRadius;
    private float mCurAngle;
    private float mMaxRadius;

    private float mCenterX;
    private float mCenterY;

    private float mPointCount ;

    private float mRingLineWidth;
    private int mRingLineColor;
    private float mRingLineProgress;
    private int mRingLineAlpha;

    private float mVirtualWidth ;
    private float[] mVirtualRings;

    private float mRotateAngle ;

    public RingView(Context context) {
        this(context,null);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mRingLineProgress = 0f;
        mCurAngle = 0f;
        mRotateAngle = 0f;
    }

    public void initRingView(float ringLineWidth,float virtualWidth,float firstStageScale,float secondStageScale,int pointCount,int ringLineAlphe,int ringLineColor) {
        mRingLineWidth = ringLineWidth;
        mVirtualWidth = virtualWidth;
        FirstStageScale = firstStageScale;
        SecondStageScale = secondStageScale;
        mPointCount = pointCount;
        mRingLineAlpha = ringLineAlphe;
        mRingLineColor = ringLineColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCenterX = getMeasuredWidth()/2f;
        mCenterY = getMeasuredHeight()/2f;

        drawRingLine(canvas);

        canvas.save();
        canvas.rotate(mRotateAngle,mCenterX,mCenterY);
        drawVirtualRing(canvas);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mCurRadius == 0) {
            mCurRadius = Math.min(getMeasuredWidth(),getMeasuredHeight())*FirstStageScale/2f;
            mMaxRadius = Math.min(getMeasuredWidth(),getMeasuredHeight())/2f;
        }

    }

    private void drawRingLine(Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(mRingLineColor);
        mPaint.setStrokeWidth(mRingLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(mRingLineAlpha);

        RectF rectF = new RectF();
        rectF.left = mCenterX - mCurRadius;
        rectF.right = mCenterX + mCurRadius;
        rectF.top = mCenterY - mCurRadius;
        rectF.bottom = mCenterY + mCurRadius;


        if (mRingLineProgress == 1.0f) {
            canvas.drawCircle(mCenterX,mCenterY,mCurRadius,mPaint);
        } else {
            float startAngle = 0;
            float sweep = 360f/mPointCount;
            for (int i=0;i<mPointCount;i++) {
                canvas.drawArc(rectF,startAngle,sweep*mRingLineProgress,false,mPaint);
                startAngle += sweep;
            }
        }
        mPaint.setStrokeCap(Paint.Cap.BUTT);

    }

    private void initVirtualRingData() {
        float ringR0 = mCurRadius;
        float ringR1 = mCurRadius + mRingLineWidth/2f +mVirtualWidth/2f;
        float ringR2 = mCurRadius + mRingLineWidth/2f +mVirtualWidth*3/2f;
        float ringR3 = mCurRadius + mRingLineWidth/2f +mVirtualWidth*5/2f;
        mVirtualRings = new float[]{ringR0,ringR1,ringR2,ringR3};
    }

    private void drawVirtualRing(Canvas canvas) {

        if (mVirtualRings == null) return;

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);

        LinearGradient gradient = new LinearGradient(
                mCenterX,
                mCenterY,
                mCenterX + mVirtualRings[mVirtualRings.length-1],
                mCenterY,
                new int[]{mRingLineColor & 0x00ffffff, mRingLineColor},
                null,
                Shader.TileMode.CLAMP
        );
        mPaint.setShader(gradient);

        for (int i = 0; i< mVirtualRings.length;i++) {
            if (i == 0) {
                mPaint.setStrokeWidth(mRingLineWidth);
            } else {
                mPaint.setStrokeWidth(mVirtualWidth);
            }
            mPaint.setAlpha(40*(mVirtualRings.length-i));
            RectF rectF = new RectF();
            rectF.left = mCenterX - mVirtualRings[i];
            rectF.right = mCenterX + mVirtualRings[i];
            rectF.top = mCenterY - mVirtualRings[i] + i*4;
            rectF.bottom = mCenterY + mVirtualRings[i] - i*4;
            int decline = 8*i;
            canvas.drawArc(rectF,-90f + decline/2f,180-decline,false,mPaint);
        }
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setShader(null);
    }

    private long mRingLineDuration = 500;
    private ObjectAnimator mRingLineAnim;
    private void startRingLineAnim() {
        if (mRingLineAnim != null && mRingLineAnim.isRunning()) {
            mRingLineAnim.cancel();
            mRingLineAnim.removeAllListeners();
        }
        mRingLineAnim = ObjectAnimator.ofFloat(this,"ringLineProgress",0f,1.0f);
        mRingLineAnim.setDuration(mRingLineDuration);
        mRingLineAnim.setInterpolator(new AccelerateInterpolator());
        mRingLineAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startLargeAnim();
            }
        });
        mRingLineAnim.start();
    }
    private void setRingLineProgress(float ringLineProgress) {
        this.mRingLineProgress = ringLineProgress;
        invalidate();
    }

    private long mLargeDuration = 1000;
    private ObjectAnimator mLargeAnim;
    private void startLargeAnim() {
        mCurRadius = mMaxRadius*FirstStageScale;
        if (mLargeAnim != null && mLargeAnim.isRunning()) {
            mLargeAnim.cancel();
            mLargeAnim.removeAllListeners();
        }
        mLargeAnim = ObjectAnimator.ofFloat(this,"curRadius",mMaxRadius*FirstStageScale,mMaxRadius*SecondStageScale);
        mLargeAnim.setInterpolator(new OvershootInterpolator());
        mLargeAnim.setDuration(mLargeDuration);
        mLargeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startRotateAnim();
            }
        });
        mLargeAnim.setStartDelay(100);
        mLargeAnim.start();
    }
    private void setCurRadius(float curRadius) {
        this.mCurRadius = curRadius;
        initVirtualRingData();
        invalidate();
    }

    private long mRotateAnimDuration = 10000;
    private ObjectAnimator mRotateAnim;
    public void startRotateAnim() {
        if (mRotateAnim != null && mRotateAnim.isRunning()) {
            return;
        }
        mRotateAnim = ObjectAnimator.ofFloat(this,"rotateAngle",mRotateAngle,mRotateAngle+360f);
        mRotateAnim.setDuration(mRotateAnimDuration);
        mRotateAnim.setInterpolator(new LinearInterpolator());
        mRotateAnim.setRepeatCount(ValueAnimator.INFINITE);
        mRotateAnim.start();
    }
    public void setRotateAngle(float rotateAngle) {
        this.mRotateAngle = rotateAngle;
        invalidate();
    }


    public void onStartAnim(){
        if (this.mRingLineProgress != 1.0f ) {
            startRingLineAnim();
        } else {
            startLargeAnim();
        }
    }

    public void onEndAnim() {
        if (mRingLineAnim != null) {
            mRingLineAnim.cancel();
            mRingLineAnim.removeAllListeners();
        }
        if (mLargeAnim != null) {
            mLargeAnim.cancel();
            mLargeAnim.removeAllListeners();
        }
        if (mRotateAnim != null && mRotateAnim.isRunning()) {
            mRotateAnim.cancel();
        }
        mVirtualRings = null;
    }

    public void setRingLineColor(int ringLineColor) {
        this.mRingLineColor = ringLineColor;
        invalidate();
    }
}
