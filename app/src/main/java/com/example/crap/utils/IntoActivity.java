package com.example.crap.utils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.crap.MainActivity;
import com.example.crap.R;
import com.example.crap.adaptor.IntroViewPagerAdaptor;
import com.example.crap.model.ScreenItem;

import java.util.ArrayList;
import java.util.List;

public class IntoActivity extends AppCompatActivity {

    ViewPager screenPager;
    IntroViewPagerAdaptor introViewPagerAdaptor;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on fullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // hide the action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        // When this activity is about to launch we will check
        // in shared preference. if user saw intro

        if(restorePrefData()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        setContentView(R.layout.activity_into);

        // init views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_getStarted);
        tabIndicator = findViewById(R.id.tab_indicator);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.button_animation);

        // fill list screen;
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Select Destination",
                "You can select the linked destination from the place you are currently on.",
                R.drawable.tap));
        mList.add(new ScreenItem("Watch All Traffic",
                "You can see all the vehicle around you based on your destination.",
                R.drawable.eye));
        mList.add(new ScreenItem("Vehicle Driver Can See You Too",
                "Which means they will FIND YOU GRAB YOU & DROP YOU to your selected destination.",
                R.drawable.map));
        mList.add(new ScreenItem("Which Means",
                "You can save time and energy",
                R.drawable.timer));

        // setup viewpager
        screenPager = findViewById(R.id.screen_pager);
        introViewPagerAdaptor = new IntroViewPagerAdaptor(this, mList);
        screenPager.setAdapter(introViewPagerAdaptor);

        // setup tab layout with viewpager
         tabIndicator.setupWithViewPager(screenPager);

        // next button click Listener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                   position++;
                   screenPager.setCurrentItem(position);

                }

                if (position == mList.size() - 1) {
                   // when we reach to the last screen
                    if(btnGetStarted.getVisibility() == View.INVISIBLE){
                        loadLastScreen();
                    }
                }
            }
        });

        // tab layout add change listener

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mList.size() - 1) {
                    if(btnGetStarted.getVisibility() == View.INVISIBLE){
                        loadLastScreen();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Get Started button Click Listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // open main activity
                Intent mainActivity = new Intent(IntoActivity.this, MainActivity.class);
                startActivity(mainActivity);
                // preventing user to click this button twice
                btnGetStarted.setEnabled(false);

                // also we need to save a boolean value to storage for
                // later time check when will know that he/she is already checked the
                // intro slider\activity

                // we gonna use shared preference
                savePrefData();
                finish();
            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("crap", MODE_PRIVATE);
        return preferences.getBoolean("isIntroCheckedByUser", false);
    }

    private void savePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("crap", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroCheckedByUser", true);
        editor.apply();
    }

    // show the GET STARTED button and hide the indicator and the next button as well
    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // setup animation of button
        btnGetStarted.setAnimation(btnAnim);
    }
}
