package com.wx.touringcarlayoutview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by wangxuan on 2017/12/5.
 */

public class TouringCarLayoutView extends RelativeLayout{

    private RingView mRingView;
    private ContentView mContentView;
    private BesselBgView mBesselBgView;

    public TouringCarLayoutView(Context context) {
        this(context,null);
    }

    public TouringCarLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouringCarLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.TouringCarLayoutView);

        String topTitle = typedArray.getString(R.styleable.TouringCarLayoutView_top_title);
        String centerTitle = typedArray.getString(R.styleable.TouringCarLayoutView_center_title);
        String bottomTitle = typedArray.getString(R.styleable.TouringCarLayoutView_bottom_title);

        int topColor = typedArray.getColor(R.styleable.TouringCarLayoutView_top_title_color, Color.WHITE);
        int centerColor = typedArray.getColor(R.styleable.TouringCarLayoutView_center_title_color, Color.WHITE) ;
        int bottomColor = typedArray.getColor(R.styleable.TouringCarLayoutView_bottom_title_color, Color.WHITE);

        float topSize = typedArray.getDimension(R.styleable.TouringCarLayoutView_top_title_size,DensityUtil.sptopx(getContext(),14f));
        float centerSize = typedArray.getDimension(R.styleable.TouringCarLayoutView_center_title_size,DensityUtil.sptopx(getContext(),24f));
        float bottomSize = typedArray.getDimension(R.styleable.TouringCarLayoutView_bottom_title_size,DensityUtil.sptopx(getContext(),12f));

        float topCenterGap = typedArray.getDimension(R.styleable.TouringCarLayoutView_topcenter_gap,DensityUtil.sptopx(getContext(),16f));
        float centerBottomGap = typedArray.getDimension(R.styleable.TouringCarLayoutView_bottomcenter_gap,DensityUtil.sptopx(getContext(),12f));

        float ringLineWidth = typedArray.getDimension(R.styleable.TouringCarLayoutView_ringline_width,DensityUtil.dptopx(getContext(),14f));
        float virtualWidth = typedArray.getDimension(R.styleable.TouringCarLayoutView_virtual_width,DensityUtil.dptopx(getContext(),3f));
        float firstStageScale = typedArray.getFloat(R.styleable.TouringCarLayoutView_firststage_scale,0.5f);
        float secondStageScale = typedArray.getFloat(R.styleable.TouringCarLayoutView_secondstage_scale,0.72f);
        int pointCount = typedArray.getInt(R.styleable.TouringCarLayoutView_point_count,8);
        int ringLineAlphe = typedArray.getInt(R.styleable.TouringCarLayoutView_ringline_alpha,120);
        int ringLineColor = typedArray.getColor(R.styleable.TouringCarLayoutView_ringline_color,Color.WHITE);

        typedArray.recycle();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        mBesselBgView = new BesselBgView(getContext());
        mBesselBgView.setLayoutParams(layoutParams);
        addView(mBesselBgView);

        mRingView = new RingView(getContext());
        mRingView.setLayoutParams(layoutParams);
        mRingView.initRingView(ringLineWidth,virtualWidth,firstStageScale,secondStageScale,pointCount,ringLineAlphe,ringLineColor);
        addView(mRingView);

        mContentView = new ContentView(getContext());
        mContentView.setLayoutParams(layoutParams);
        mContentView.initContentView(topTitle,centerTitle,bottomTitle,topColor,centerColor,bottomColor,topSize,centerSize,bottomSize,topCenterGap,centerBottomGap);
        addView(mContentView);
    }

    public void onStartAnim() {
        mRingView.onStartAnim();
        mContentView.onStartScaleAnim();
    }

    public void onEndAnim() {
        mRingView.onEndAnim();
        mContentView.onEndScaleAnim();
    }

    public void setRingLineColor(int ringLineColor) {
        mRingView.setRingLineColor(ringLineColor);
    }

    public void setTopTitle(String topTitle) {
        mContentView.setTopTitle(topTitle);
    }

    public void setTopTitleColor(int topTitleColor) {
        mContentView.setTopTitleColor(topTitleColor);
    }

    public void setCenterTitle(String centerTitle) {
        mContentView.setCenterTitle(centerTitle);
    }

    public void setCenterTitleColor(int centerTitleColor) {
        mContentView.setCenterTitleColor(centerTitleColor);
    }

    public void setBottomTitle(String bottomTitle) {
        mContentView.setBottomTitle(bottomTitle);
    }

    public void setBottomTitleColor(int bottomTitleColor) {
        mContentView.setBottomTitleColor(bottomTitleColor);
    }

}
