package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.LocaleManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "Base Activity";
    protected Toolbar toolbar;
    protected abstract int getLayoutResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.loadLocale(this);
        setContentView(getLayoutResource());

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        Log.d(TAG, "Is toolbar available: " + (toolbar != null ? true : false));
        Log.d(TAG, "Is toolbar available: " + (toolbar != null ? true : false));
        Log.d(TAG, "Is toolbar available: " + (toolbar != null ? true : false));

        if (toolbar != null){
            setSupportActionBar(toolbar);

            Log.d(TAG, "getSupportActionBar: " + (getSupportActionBar() != null ? true : false));

            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    protected Context getContext(){
        return this;
    }

    protected Toolbar getToolbar(){
        return toolbar;
    }

    protected void setToolbarTitle(String value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(value);
        }
    }
    protected void setToolbarSubtitle(String value) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(value);
        }
    }

    protected void setHomeAsUpEnabled(boolean up) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(up);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
