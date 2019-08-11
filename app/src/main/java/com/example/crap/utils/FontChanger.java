package com.example.crap.utils;

import android.app.Application;

import com.example.crap.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FontChanger extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // font overriding here for entire system
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway-Medium.ttf")
                .setFontAttrId(R.attr.fontPath).build()
        );
    }
}
