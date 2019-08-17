package com.example.crap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crap.adaptor.DestinationListAdaptor;
import com.example.crap.store.DestinationCollection;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawer;
    RecyclerView recyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    DestinationListAdaptor destinationListAdaptor;
    DestinationCollection destinationCollection;
    CircleImageView circleImageView;
    SwipeRefreshLayout pullToRefresh;
    boolean isAboutToMoveAnotherActivity = false;

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
        destinationCollection = new DestinationCollection();

        recyclerView = findViewById(R.id.destinationList);
        destinationListAdaptor = new DestinationListAdaptor(this, destinationCollection.getDestinations());

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.setAdapter(destinationListAdaptor);

        destinationListAdaptor.setOnClickListener(new DestinationListAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Looking For " +
                                destinationCollection.getDestinations().get(position).getName(),
                                Toast.LENGTH_LONG).show();
            }
        });

        circleImageView = findViewById(R.id.profile_image);


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] punches = {"Ah! please stop tapping me", "Please, it's Paining", "Hope it's you Gaurav", "I am just an Avatar"};
                new SimpleTooltip.Builder(getApplicationContext())
                        .anchorView(view)
                        .textColor(Color.WHITE)
                        .text(punches[(int) (Math.random() * punches.length)])
                        .gravity(Gravity.START)
                        .dismissOnOutsideTouch(true)
                        .dismissOnInsideTouch(true)
                        .transparentOverlay(true)
                        .modal(true)
                        .focusable(true)
                        .build()
                        .show();
            }
        });

        pullToRefresh = findViewById(R.id.loopCard_pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "Fetching Again", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        isAboutToMoveAnotherActivity = false;
    }

    @Override
    public void onBackPressed() {
        if(mDrawer.isDrawerOpen(Gravity.START)) {
            mDrawer.closeDrawer(Gravity.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        if(!isAboutToMoveAnotherActivity){
            switch (item.getItemId()) {
                case R.id.settings: {
                    Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_LONG).show();
                    break;
                }case R.id.payments_details: {
                    Toast.makeText(getApplicationContext(), "Payment Details", Toast.LENGTH_LONG).show();
                    break;
                }case R.id.about: {
                    startActivity(new Intent(MainActivity.this, About.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    break;
                }case R.id.logout: {
                    Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
        isAboutToMoveAnotherActivity = true;
        //close navigation drawer
//        mDrawer.closeDrawer(Gravity.START);
        return true;
    }

    public void setNavigationViewListener() {
        NavigationView navigationView = findViewById(R.id.loopNav);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
