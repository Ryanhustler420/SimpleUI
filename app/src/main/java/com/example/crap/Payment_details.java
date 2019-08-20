package com.example.crap;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import com.example.crap.adaptor.PaymentListAdaptor;
import com.example.crap.store.PaymentCollection;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Payment_details extends AppCompatActivity {

    Button backbtn;
    RecyclerView recyclerView;
    PaymentListAdaptor paymentListAdaptor;
    PaymentCollection paymentCollection;
    StaggeredGridLayoutManager staggeredGridLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        paymentCollection = new PaymentCollection();

        recyclerView = findViewById(R.id.payments_list);
        paymentListAdaptor = new PaymentListAdaptor(this, paymentCollection.getPayemtsCollections());

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        recyclerView.setAdapter(paymentListAdaptor);

        paymentListAdaptor.setOnClickListener(new PaymentListAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // code here for simple item click
            }
        });

        backbtn = findViewById(R.id.payments_details_back_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
