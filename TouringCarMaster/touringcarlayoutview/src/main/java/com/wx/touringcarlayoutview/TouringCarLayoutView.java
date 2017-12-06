package com.wx.touringcarlayoutview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by wangxuan on 2017/12/5.
 */

public class TouringCarLayoutView extends RelativeLayout{

    private SlogenView mSlogenView;

    public TouringCarLayoutView(Context context) {
        this(context,null);
    }

    public TouringCarLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouringCarLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        mSlogenView = new SlogenView(getContext());
        mSlogenView.setLayoutParams(layoutParams);
        addView(mSlogenView);
    }

}
