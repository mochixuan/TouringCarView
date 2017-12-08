package com.wx.touringcarlayoutview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private float mHeightScale = 0.2f;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        mPaint.setColor(Color.RED);
        Path path = new Path();

        LinearGradient gradient1 = new LinearGradient(
                0,getMeasuredHeight()*(1-mHeightScale),
                getMeasuredWidth()/4f,getMeasuredHeight(),
                0x66f5f5f5,
                0x88B23AEE,
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
