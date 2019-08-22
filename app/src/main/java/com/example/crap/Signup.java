package com.example.crap;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

// Data types for Different Spinner's
enum DataType { CITY, STATE, COUNTRY }

public class Signup extends AppCompatActivity {

    static String COUNTRY = "Select Country";
    static String STATE = "Select State";
    static String CITY = "Select City";

    Spinner countrySpinner, stateSpinner, citySpinner;
    private static final String[] country = { COUNTRY , "India", "Pakistan", "USA", "China", "Japan"};
    private static final String[] states = { STATE , "Jharkhand", "Delhi", "Banglore", "Mumbai", "Bihar", "Panjab"};
    private static final String[] cities = { CITY , "Jamshedpur", "Ranchi", "Dhanbad", "Bokaro", "Hazaribhag"};

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
                String currentCountrySelected = countrySpinner.getSelectedItem().toString();
                String currentStateSelected = stateSpinner.getSelectedItem().toString();
                switch (type) {
                    case COUNTRY:
                            stateSpinner.setSelection(0);
                            citySpinner.setSelection(0);
                            if(currentCountrySelected.equals(COUNTRY)) {
                                stateSpinner.setEnabled(false);
                            }
                            if(!currentCountrySelected.equals(COUNTRY)) {
                                stateSpinner.setEnabled(true);
                            }
                            citySpinner.setEnabled(false);
                        break;
                    case STATE:
                        if(currentStateSelected.equals(STATE)) {
                            citySpinner.setEnabled(false);
                        }

                        if(!currentStateSelected.equals(STATE)) {
                            citySpinner.setEnabled(true);
                        }
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
