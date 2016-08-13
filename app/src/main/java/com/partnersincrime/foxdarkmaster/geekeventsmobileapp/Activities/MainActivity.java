package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers.DataTask;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.SPManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DataTask.IConnectionListener {

    private static final String TAG = "MainActivity";

    private AsyncTask task;

    private Toolbar toolbar;

    private Button buttonActivities;
    private Button buttonMap;
    private Button buttonInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Fabric.with(this, new Crashlytics());
        setContentView(R.layout.main_activity_menu);

        setActionBar();
        setupData();
        setupInterface();
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(getResources().getString(R.string.app_name));
    }

    private void setupData() {

        if (Utils.isNetworkAvailable(this)) {
            task = new DataTask(this, DataTask.ACTIVITIES_GET, this);
            task.execute();
        } else {
            // TODO load default data
        }




        // ActivitiesManager.getInstance();
    }

    private void setupInterface() {
        buttonActivities = (Button) findViewById(R.id.buttonActivities);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonInformation = (Button) findViewById(R.id.buttonInfo);

        buttonActivities.setOnClickListener(this);
        buttonMap.setOnClickListener(this);
        buttonInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonActivities.getId()) {
            Intent intent = new Intent(this, ActivityContainerActivities.class);
            startActivity(intent);
        } else if (v.getId() == buttonMap.getId()) {
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        } else if (v.getId() == buttonInformation.getId()) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPre() {
        // TODO Show progress bar
    }

    @Override
    public void onResult(JSONObject result) {
        Log.d(TAG, "DEBUG onResult");

        try {
            SPManager.setActivities(this, result.getJSONArray("data").toString());
        } catch(JSONException e) {
            e.printStackTrace();
        }



        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");
        Log.d(TAG, "DEBUG onResult :D");

        Log.d(TAG, "DEBUG onResult: " + result);
    }

    @Override
    public void onError(int code) {
        // TODO Hide progress bar
        Log.d(TAG, "DEBUG onError: " + code);
    }
}
