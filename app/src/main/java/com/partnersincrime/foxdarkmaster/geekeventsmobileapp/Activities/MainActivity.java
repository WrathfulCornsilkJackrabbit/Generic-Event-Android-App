package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    Button buttonActivities;
    Button buttonMap;
    Button buttonInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_menu);
        setupData();
    }

    private void setupData() {
        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonInformation = (Button) findViewById(R.id.buttonInfo);

        Log.d(TAG, "DEBUG Element 1 to call");
        buttonActivities.setOnClickListener(this);
        Log.d(TAG, "DEBUG Element 2 to call");
        buttonMap.setOnClickListener(this);
        Log.d(TAG, "DEBUG Element 3 to call");
        buttonInformation.setOnClickListener(this);
        Log.d(TAG, "DEBUG Element 4 to call");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonActivities.getId()) {
            Log.d(TAG, "Button activities");

            //Intent intent = new Intent(this, ActivitiesActivity.class);
            Intent intent = new Intent(this, ActivityContainerActivities.class);
            //intent.putExtra("OBJ", obj);
            startActivity(intent);
        } else if (v.getId() == buttonMap.getId()) {
            Log.d(TAG, "Button map");

            Intent intent = new Intent(this, MapActivity.class);
            //intent.putExtra("OBJ", obj);
            startActivity(intent);
        } else if (v.getId() == buttonInformation.getId()) {
            Log.d(TAG, "Button info");

            Intent intent = new Intent(this, InfoActivity.class);
            //intent.putExtra("OBJ", obj);
            startActivity(intent);
        }
    }
}
