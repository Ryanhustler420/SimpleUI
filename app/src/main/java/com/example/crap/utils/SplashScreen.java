package com.example.crap.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.crap.MainActivity;
import com.example.crap.R;

public class SplashScreen extends Activity {

    static int splashTimeout = 1500;
    static Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        // make the activity on fullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler = new Handler();
        handler.postDelayed(timer, splashTimeout);
    }

    private Runnable timer = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            overridePendingTransition(R.anim.enter, R.anim.exit);
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        handler.removeCallbacks(timer);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(timer);
        finish();
        super.onPause();
    }
}
