package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers.DataTask;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.SPManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

import io.fabric.sdk.android.Fabric;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DataTask.IConnectionListener {

    private static final String TAG = "MainActivity";

    private AsyncTask task;
    private ActivityModel activities;

    private Toolbar toolbar;

    private Button buttonActivities;
    private Button buttonMap;
    private Button buttonInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.main_activity_menu);

        setActionBar();
        getOnlineData();
        setupInterface();
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_full_title));
    }

    private void getOnlineData() {
        if (Utils.isNetworkAvailable(this)) {
            task = new DataTask(this, DataTask.ACTIVITIES_GET, this);
            task.execute();
        } else {
            setupData();
        }
    }

    private void setupInterface() {
        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonInformation = (Button) findViewById(R.id.buttonInfo);


        // TODO Remove hide
        buttonMap.setVisibility(View.GONE);
        buttonInformation.setVisibility(View.GONE);


        buttonActivities.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
        buttonInformation.setOnClickListener(this);
    }

    private void setupData() {
        ActivitiesManager.getInstance().setCurrentContent(this);
        ActivitiesManager.getInstance().setupData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonActivities.getId()) {
            if (checkIfDataIsPresent()) {
                Intent intent = new Intent(this, ActivityContainerActivities.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this,
                        Utils.getErrorString(MainActivity.this, 0),
                        Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == buttonMap.getId()) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else if (v.getId() == buttonInformation.getId()) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkIfDataIsPresent() {
        ActivityModel activitiesDay1[] = ActivitiesManager.getInstance().getRawDataDay1();
        ActivityModel activitiesDay2[] = ActivitiesManager.getInstance().getRawDataDay2();

        if (activitiesDay1 != null && activitiesDay2 != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onPre() {
        // TODO Show progress bar
    }

    @Override
    public void onResult(JSONObject result) {
        /*
        try {
            //SPManager.setActivities(this, result.getJSONObject("data").toString());

            String resultDay1 = result.getJSONArray("2016-08-20").toString();
            SPManager.setActivitiesByDay(this, 1, resultDay1);

            String resultDay2 = result.getJSONArray("2016-08-21").toString();
            SPManager.setActivitiesByDay(this, 2, resultDay2);

            JSONObject resultDay1 = result.getJSONObject("data")
                    .getJSONObject("2016-08-20");
            SPManager.setActivitiesByDay(this, 1, resultDay1.toString());

            JSONObject resultDay2 = result.getJSONObject("data")
                    .getJSONObject("2016-08-21");
            SPManager.setActivitiesByDay(this, 2, resultDay2.toString());


            setupData();
        } catch(JSONException e) {
            e.printStackTrace();
        }
        */

        try {
            String resultDay1 = result.getJSONObject("data")
                    .getJSONArray("2016-08-20")
                    .toString();
            SPManager.setActivitiesByDay(this, 1, resultDay1);

            String resultDay2 = result.getJSONObject("data")
                    .getJSONArray("2016-08-21")
                    .toString();
            SPManager.setActivitiesByDay(this, 2, resultDay2);


            setupData();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(int code) {
        // TODO Hide progress bar
        Log.d(TAG, "DEBUG onError: " + code);

        setupData();
    }
}
