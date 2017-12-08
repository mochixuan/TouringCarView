package com.wx.touringcarlayoutview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by wangxuan on 2017/12/8.
 */

public class ContentView extends View{

    private Paint mPaint;

    private float mCenterX;
    private float mCenterY;

    private String mTopTitle;
    private int mTopTitleColor ;
    private float mTopTitleTextSize ;

    private String mCenterTitle;
    private int mCenterTitleColor ;
    private float mCenterTitleTextSize ;

    private String mBottomTitle;
    private int mBottomTitleColor ;
    private float mBottomTitleTextSize ;

    private float mCenterBottomGap ;
    private float mTopCenterGap ;
    private float mCurScale = 0f;

    public ContentView(Context context) {
        this(context,null);
    }

    public ContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void initContentView(
            String topTitle,String centerTitle,String bottomTitle,
            int topColor,int centerColor,int bottomColor,
            float topSize,float centerSize,float bottomSize,
            float centerBottomGap,float topCenterGap) {

        mTopTitle = topTitle;
        mCenterTitle = centerTitle;
        mBottomTitle = bottomTitle;

        mTopTitleColor  =topColor;
        mCenterTitleColor = centerColor;
        mBottomTitleColor = bottomColor;

        mTopTitleTextSize = topSize;
        mCenterTitleTextSize = centerSize;
        mBottomTitleTextSize = bottomSize;

        mTopCenterGap = topCenterGap;
        mCenterBottomGap = centerBottomGap;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCenterX = getMeasuredWidth()/2f;
        mCenterY = getMeasuredHeight()/2f;

        canvas.save();
        canvas.scale(mCurScale,mCurScale,mCenterX,mCenterY);

        float centerTitleHight = 0f;

        if (mCenterTitle != null && mCenterTitle.length()>0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mPaint.setLetterSpacing(0.3f);
            }
            mPaint.setColor(mCenterTitleColor);
            mPaint.setStrokeWidth(1);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setTextSize(mCenterTitleTextSize);
            mPaint.setTextAlign(Paint.Align.CENTER);
            centerTitleHight = -mPaint.ascent() - mPaint.descent();
            mPaint.setFakeBoldText(true);
            canvas.drawText(mCenterTitle,mCenterX,mCenterY + centerTitleHight/2f,mPaint);
            mPaint.setFakeBoldText(false);
        }
        if (mBottomTitle != null && mBottomTitle.length()>0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mPaint.setLetterSpacing(0.1f);
            }
            mPaint.setColor(mBottomTitleColor);
            mPaint.setTextSize(mBottomTitleTextSize);
            float bottomTitleHeight = -mPaint.ascent() - mPaint.descent();
            canvas.drawText(mBottomTitle,mCenterX,mCenterY+bottomTitleHeight/2f+centerTitleHight/2f+mCenterBottomGap,mPaint);
        }
        if (mTopTitle != null && mTopTitle.length()>0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mPaint.setLetterSpacing(0.1f);
            }
            mPaint.setColor(mTopTitleColor);
            mPaint.setTextSize(mTopTitleTextSize);
            float topTitleHeight = -mPaint.ascent() - mPaint.descent();
            canvas.drawText(mTopTitle,mCenterX,mCenterY-centerTitleHight/2f-topTitleHeight/2f-mTopCenterGap,mPaint);
        }

        canvas.restore();
    }

    private ObjectAnimator mScaleAnim;
    private long mScaleAnimDuration = 1000;
    public void onStartScaleAnim() {
        setCurScale(0f);
        if (mScaleAnim != null && mScaleAnim.isRunning()) {
            mScaleAnim.cancel();
            mScaleAnim.removeAllListeners();
        }
        mScaleAnim = ObjectAnimator.ofFloat(this,"curScale",0.4f,1.0f);
        mScaleAnim.setInterpolator(new OvershootInterpolator());
        mScaleAnim.setDuration(mScaleAnimDuration);
        mScaleAnim.setStartDelay(100);
        mScaleAnim.start();
    }
    private void setCurScale(float curScale) {
        this.mCurScale = curScale;
        invalidate();
    }

    public void onEndScaleAnim() {
        if (mScaleAnim != null) {
            mScaleAnim.cancel();
            mScaleAnim.removeAllListeners();
        }
    }

    public void setTopTitle(String topTitle) {
        this.mTopTitle = topTitle;
        invalidate();
    }

    public void setTopTitleColor(int topTitleColor) {
        this.mTopTitleColor = topTitleColor;
        invalidate();
    }

    public void setCenterTitle(String centerTitle) {
        this.mCenterTitle = centerTitle;
        invalidate();
    }

    public void setCenterTitleColor(int centerTitleColor) {
        this.mCenterTitleColor = centerTitleColor;
        invalidate();
    }

    public void setBottomTitle(String bottomTitle) {
        this.mBottomTitle = bottomTitle;
        invalidate();
    }

    public void setBottomTitleColor(int bottomTitleColor) {
        this.mBottomTitleColor = bottomTitleColor;
        invalidate();
    }
}
