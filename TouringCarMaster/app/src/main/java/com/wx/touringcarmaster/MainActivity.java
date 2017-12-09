package com.wx.touringcarmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wx.touringcarlayoutview.TouringCarLayoutView;

public class MainActivity extends AppCompatActivity {

    private TouringCarLayoutView carLayoutView;
    private boolean isConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carLayoutView = findViewById(R.id.car_view);
        carLayoutView.setBackgroundColors(new int[]{0xff7EC0EE,0xff436EEE});

        findViewById(R.id.change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carLayoutView.setConnect(isConnect);
                isConnect = !isConnect;
            }
        });
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
