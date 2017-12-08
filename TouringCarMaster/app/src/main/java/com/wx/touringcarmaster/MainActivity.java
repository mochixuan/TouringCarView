package com.wx.touringcarmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wx.touringcarlayoutview.TouringCarLayoutView;

public class MainActivity extends AppCompatActivity {

    private TouringCarLayoutView carLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carLayoutView = findViewById(R.id.car_view);
    }


    @Override
    protected void onResume() {
        super.onResume();
        carLayoutView.onStartAnim();
    }

    @Override
    protected void onPause() {
        super.onPause();
        carLayoutView.onEndAnim();
    }

}
