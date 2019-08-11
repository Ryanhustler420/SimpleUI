package com.example.crap;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = findViewById(R.id.drawer);
        Button btn = findViewById(R.id.button);
        setNavigationViewListener();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(Gravity.START);
            }
        });
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.settings: {
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                break;
            }case R.id.payments_details: {
                Toast.makeText(getApplicationContext(), "Payment Details", Toast.LENGTH_LONG).show();
                break;
            }case R.id.about: {
                Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, About.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;
            }case R.id.logout: {
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                break;
            }
        }
        //close navigation drawer
//        mDrawer.closeDrawer(Gravity.START);
        return true;
    }

    public void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.loopNav);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
