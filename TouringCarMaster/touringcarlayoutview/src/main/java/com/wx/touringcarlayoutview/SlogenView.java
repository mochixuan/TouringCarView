package com.wx.touringcarlayoutview;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxuan on 2017/12/5.
 */

public class SlogenView extends View{

    private Paint mPaint;

    private String mSlogen = "智能生活 · 智能房车";

    private final float BesselC = 0.552284749831f;
    private final float BesselHScale = 0.72f;      //小于1.0f
    private final float BesselWBScale = 1.6f;      //大于1.0f
    private final float BesselWSScale = 0.3f;      //大于1.0f
    private final float BesselCScale = 0.2f;      //小于1.0f

    private final float FirstStageProgress = 0.3f;
    private final float SecondStageProgress = 0.5f;
    private final float ThreeStageProgress = 0.2f;

    private List<BesselPointModel> mPointModels;
    private AnimatorSet mAnimator;

    private float mCenterX = 260f;
    private float mCenterY = 1000f;
    private float mProgress = 0f;

    private long mDurtion = 2000;

    public SlogenView(Context context) {
        this(context,null);
    }

    public SlogenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlogenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointModels = new ArrayList<>();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        //onStartAnim(260f,860f);
        onStartAnim(800f,260f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(1f);
        mPaint.setStyle(Paint.Style.FILL);

        BesselPointModel pointModel = generateBesselPoint(mCenterX,mCenterY,100f,false,mProgress,Color.RED);

        Path path = new Path();
        mPaint.setColor(pointModel.getColor());

        path.moveTo(pointModel.getP0().x,pointModel.getP0().y);
        path.cubicTo(
                pointModel.getP1().x,pointModel.getP1().y,
                pointModel.getP2().x,pointModel.getP2().y,
                pointModel.getP3().x,pointModel.getP3().y
        );

        path.cubicTo(
                pointModel.getP4().x,pointModel.getP4().y,
                pointModel.getP5().x,pointModel.getP5().y,
                pointModel.getP6().x,pointModel.getP6().y
        );

        path.cubicTo(
                pointModel.getP7().x,pointModel.getP7().y,
                pointModel.getP8().x,pointModel.getP8().y,
                pointModel.getP9().x,pointModel.getP9().y
        );

        path.cubicTo(
                pointModel.getP10().x,pointModel.getP10().y,
                pointModel.getP11().x,pointModel.getP11().y,
                pointModel.getP0().x,pointModel.getP0().y
        );


        canvas.drawPath(path,mPaint);

    }

    //生成不同形状的贝塞尔图像
    private BesselPointModel generateBesselPoint(float centerX,float centerY , float radius , boolean isToRight , float progress , int color) {

        float topbottomRadius,rightRadius,rightBesselC,leftRadius,leftBesselC ;

        //拉出阶段
        if (progress <= FirstStageProgress) {
            float newProgress = progress/FirstStageProgress;
            topbottomRadius = radius;
            rightRadius = radius * (1 + (BesselWBScale-1)*newProgress);
            rightBesselC = BesselC - BesselCScale*newProgress;
            leftRadius = radius;
            leftBesselC = BesselC;
        } else if (progress <= FirstStageProgress + SecondStageProgress) {
            float newProgress = (progress-FirstStageProgress)/SecondStageProgress;
            rightRadius = radius * (BesselWBScale - (BesselWBScale-1)*newProgress);
            rightBesselC = BesselC - BesselCScale*(1-newProgress);
            //得出对称进度: 0-1-0
            newProgress = (0.5f - Math.abs(newProgress-0.5f))*2;
            topbottomRadius = radius*(1 - (1-BesselHScale)*newProgress);
            leftRadius = radius * (1+(BesselWBScale-1)*newProgress);
            leftBesselC = BesselC - BesselCScale*newProgress;
        } else {
            float newProgress = (progress-FirstStageProgress-SecondStageProgress)/ThreeStageProgress;
            //得出对称进度: 0-1-0
            newProgress = (0.5f - Math.abs(newProgress-0.5f))*2;
            topbottomRadius = radius;
            rightRadius = radius * (1 - BesselWSScale*newProgress/6f);
            rightBesselC = BesselC + BesselCScale*newProgress;
            leftRadius = radius * (1 - BesselWSScale*newProgress);
            leftBesselC = BesselC + BesselCScale*newProgress;
        }

        //切换位置
        if (!isToRight) {
            float middleKey = rightRadius;
            rightRadius = leftRadius;
            leftRadius = middleKey;
            middleKey = rightBesselC;
            rightBesselC = leftBesselC;
            leftBesselC = middleKey;
        }

        PointF p11 = new PointF(centerX - topbottomRadius*BesselC,centerY - topbottomRadius);
        PointF p0 = new PointF(centerX,centerY - topbottomRadius);
        PointF p1 = new PointF(centerX + topbottomRadius*BesselC,centerY - topbottomRadius);

        PointF p2 = new PointF(centerX + rightRadius,centerY - rightRadius*rightBesselC);
        PointF p3 = new PointF(centerX + rightRadius,centerY);
        PointF p4 = new PointF(centerX + rightRadius,centerY + rightRadius*rightBesselC);

        PointF p5 = new PointF(centerX + topbottomRadius*BesselC,centerY + topbottomRadius);
        PointF p6 = new PointF(centerX ,centerY + topbottomRadius);
        PointF p7 = new PointF(centerX - topbottomRadius*BesselC,centerY + topbottomRadius);

        PointF p8 = new PointF(centerX - leftRadius,centerY + leftRadius*leftBesselC);
        PointF p9 = new PointF(centerX - leftRadius,centerY);
        PointF p10 = new PointF(centerX - leftRadius,centerY - leftRadius*leftBesselC);

        BesselPointModel pointModel = new BesselPointModel(
                centerX, centerY,
                p0, p1, p2, p3,
                p4, p5, p6, p7,
                p8, p9, p10, p11,
                radius,isToRight,color
        );

        return pointModel;
    }

    private void onStartAnim(final float start, final float end) {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
            mAnimator.removeAllListeners();
        }
        mAnimator = new AnimatorSet();

        ValueAnimator firstAnimator = new ValueAnimator();
        firstAnimator.setFloatValues(0f,1f);
        firstAnimator.setDuration((long) (mDurtion*FirstStageProgress));
        firstAnimator.setInterpolator(new LinearInterpolator());
        firstAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mProgress = valueAnimator.getAnimatedFraction()*FirstStageProgress;
                mCenterX = start;
                invalidate();
            }
        });

        ValueAnimator sencondAnimtor = new ValueAnimator();
        sencondAnimtor.setFloatValues(start,end);
        sencondAnimtor.setDuration((long) (mDurtion*SecondStageProgress));
        sencondAnimtor.setInterpolator(new LinearInterpolator());
        sencondAnimtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mProgress = FirstStageProgress+(valueAnimator.getAnimatedFraction()*SecondStageProgress);
                mCenterX = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator threeAnimtor = new ValueAnimator();
        threeAnimtor.setFloatValues(0f,1f);
        threeAnimtor.setDuration((long) (mDurtion*ThreeStageProgress));
        threeAnimtor.setInterpolator(new LinearInterpolator());
        threeAnimtor.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mProgress = FirstStageProgress + SecondStageProgress + (valueAnimator.getAnimatedFraction()*ThreeStageProgress);
                mCenterX = end;
                invalidate();
            }
        });

        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.playSequentially(firstAnimator,sencondAnimtor,threeAnimtor);
        mAnimator.start();
    }

}
