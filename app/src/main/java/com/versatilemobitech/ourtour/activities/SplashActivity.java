package com.versatilemobitech.ourtour.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.utils.Constants;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_splash);

        Handler mSplashHandler = new Handler();
        Runnable action = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mSplashHandler.postDelayed(action, Constants.SPLASH_TIME_OUT);
    }
}
