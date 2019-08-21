package com.example.crap;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

// Data types for Different Spinner's
enum DataType { CITY, STATE, COUNTRY }

public class Signup extends AppCompatActivity {

    Spinner countrySpinner, stateSpinner, citySpinner;
    private static final String[] country = {"Select Country", "India", "Pakistan", "USA", "China", "Japan"};
    private static final String[] states = {"Select State", "Jharkhand", "Delhi", "Banglore", "Mumbai", "Bihar", "Panjab"};
    private static final String[] cities = {"Select City", "Jamshedpur", "Ranchi", "Dhanbad", "Bokaro", "Hazaribhag"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Country Spinner Populating here
        countrySpinner = findViewById(R.id.sign_up_form_country_spinner);
        setSpinnerData(countrySpinner, country, DataType.COUNTRY);

        // State Spinner Populating here
        stateSpinner = findViewById(R.id.sign_up_form_state_spinner);
        setSpinnerData(stateSpinner, states, DataType.STATE);

        // City Spinner Populating here
        citySpinner = findViewById(R.id.sign_up_form_city_spinner);
        setSpinnerData(citySpinner, cities, DataType.CITY);

    }

    public void setSpinnerData(Spinner spinner, final String[] data, final DataType type) {
        ArrayAdapter<String> adaptor = new ArrayAdapter<>(Signup.this, android.R.layout.simple_spinner_dropdown_item, data);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptor);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: set value to form object later
                // Toast.makeText(getApplicationContext(), data[i], Toast.LENGTH_SHORT).show();
                switch (type) {
                    case COUNTRY:
                            stateSpinner.setSelection(0);
                            citySpinner.setSelection(0);
                        break;
                    case STATE:
                            citySpinner.setSelection(0);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { /* code here later */ }
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
