package com.wx.touringcarlayoutview;

import android.graphics.PointF;
import android.support.annotation.ColorInt;

/**
 * Created by wangxuan on 2017/12/6.
 */

public class BesselPointModel {

    //中心点，相当于画圆时的圆心
    private float centerX;
    private float centerY;

    //12个点绘制圆
    private PointF p0;
    private PointF p1;
    private PointF p2;
    private PointF p3;
    private PointF p4;
    private PointF p5;
    private PointF p6;
    private PointF p7;
    private PointF p8;
    private PointF p9;
    private PointF p10;
    private PointF p11;

    private float radius;
    private boolean isToRight;

    //加个颜色，每个都可以单独颜色
    @ColorInt
    private int color;

    public BesselPointModel() {
    }

    public BesselPointModel(float centerX, float centerY, PointF p0, PointF p1, PointF p2, PointF p3, PointF p4, PointF p5, PointF p6, PointF p7, PointF p8, PointF p9, PointF p10, PointF p11,float radius, boolean isToRight, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.p7 = p7;
        this.p8 = p8;
        this.p9 = p9;
        this.p10 = p10;
        this.p11 = p11;
        this.radius = radius;
        this.isToRight = isToRight;
        this.color = color;
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public PointF getP0() {
        return p0;
    }

    public void setP0(PointF p0) {
        this.p0 = p0;
    }

    public PointF getP1() {
        return p1;
    }

    public void setP1(PointF p1) {
        this.p1 = p1;
    }

    public PointF getP2() {
        return p2;
    }

    public void setP2(PointF p2) {
        this.p2 = p2;
    }

    public PointF getP3() {
        return p3;
    }

    public void setP3(PointF p3) {
        this.p3 = p3;
    }

    public PointF getP4() {
        return p4;
    }

    public void setP4(PointF p4) {
        this.p4 = p4;
    }

    public PointF getP5() {
        return p5;
    }

    public void setP5(PointF p5) {
        this.p5 = p5;
    }

    public PointF getP6() {
        return p6;
    }

    public void setP6(PointF p6) {
        this.p6 = p6;
    }

    public PointF getP7() {
        return p7;
    }

    public void setP7(PointF p7) {
        this.p7 = p7;
    }

    public PointF getP8() {
        return p8;
    }

    public void setP8(PointF p8) {
        this.p8 = p8;
    }

    public PointF getP9() {
        return p9;
    }

    public void setP9(PointF p9) {
        this.p9 = p9;
    }

    public PointF getP10() {
        return p10;
    }

    public void setP10(PointF p10) {
        this.p10 = p10;
    }

    public PointF getP11() {
        return p11;
    }

    public void setP11(PointF p11) {
        this.p11 = p11;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isToRight() {
        return isToRight;
    }

    public void setToRight(boolean toRight) {
        isToRight = toRight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
