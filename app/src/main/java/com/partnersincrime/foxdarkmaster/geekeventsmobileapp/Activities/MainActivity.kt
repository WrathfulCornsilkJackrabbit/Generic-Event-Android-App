package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.crashlytics.android.Crashlytics
import com.google.gson.Gson
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Handlers.DataTask
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.SPManager
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils

import io.fabric.sdk.android.Fabric
import org.json.JSONException
import org.json.JSONObject

class MainActivity : BaseActivity(), View.OnClickListener, DataTask.IConnectionListener {

    private var task: AsyncTask<*, *, *>? = null

    private var buttonActivities: Button? = null
    private var buttonMap: Button? = null
    private var buttonInformation: Button? = null

    private var mMainTextDays: TextView? = null
    private var mMainTextMonth: TextView? = null
    private var mMainTextLocation: TextView? = null
    private var mMainTextGreeting: TextView? = null

    override fun getLayoutResource(): Int {
        return R.layout.main_activity_menu
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.main_activity_menu)

        getOnlineData()
        setupInterface()
    }

    private fun getOnlineData() {
        if (Utils.isNetworkAvailable(this)) {
            task = DataTask(this, DataTask.ACTIVITIES_GET, this)
            task!!.execute()
        } else {
            setupData()
        }
    }

    private fun setupInterface() {
        buttonActivities = findViewById<View>(R.id.buttonActivities) as Button
        buttonMap = findViewById<View>(R.id.buttonMap) as Button
        buttonInformation = findViewById<View>(R.id.buttonInfo) as Button
        //
        mMainTextDays = findViewById<View>(R.id.main_text_event_days) as TextView
        mMainTextMonth = findViewById<View>(R.id.main_text_event_month) as TextView
        mMainTextLocation = findViewById<View>(R.id.main_text_location) as TextView
        mMainTextGreeting = findViewById<View>(R.id.main_text_greeting) as TextView
        //
        //        buttonActivities.setTypeface(Utils.getRegularFont(this));
        //        buttonMap.setTypeface(Utils.getRegularFont(this));
        //        buttonInformation.setTypeface(Utils.getRegularFont(this));

        //        mMainTextDays.setTypeface(Utils.getTitleFont(this));
        //        mMainTextMonth.setTypeface(Utils.getTitleFont(this));
        //        mMainTextLocation.setTypeface(Utils.getRegularBoldFont(this));
        //        mMainTextGreeting.setTypeface(Utils.getRegularBoldFont(this));

        // TODO Temporarely hide information option
        //        buttonInformation.setVisibility(View.GONE);


        buttonActivities!!.setOnClickListener(this)
        buttonMap!!.setOnClickListener(this)
        buttonInformation!!.setOnClickListener(this)
    }

    private fun setupData() {
        ActivitiesManager.getInstance().currentContent = this
        ActivitiesManager.getInstance().setupData()
    }

    override fun onClick(v: View) {
        if (v.id == buttonActivities!!.id) {
            val intent = Intent(this, ActivityContainerActivities::class.java)
            startActivity(intent)

            /*
            if (checkIfDataIsPresent()) {
                Intent intent = new Intent(this, ActivityContainerActivities.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this,
                        Utils.getErrorString(MainActivity.this, 0),
                        Toast.LENGTH_LONG).show();
            }
            */
        } else if (v.id == buttonMap!!.id) {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        } else if (v.id == buttonInformation!!.id) {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkIfDataIsPresent(): Boolean {
        val activitiesDay1 = ActivitiesManager.getInstance().rawDataDay1
        val activitiesDay2 = ActivitiesManager.getInstance().rawDataDay2

        return if (activitiesDay1 != null && activitiesDay2 != null) {
            true
        } else {
            false
        }
    }

    override fun onPre() {
        // TODO Show progress bar
    }

    override fun onResult(result: JSONObject) {
        try {
            val resultDay1 = result.getJSONObject("data")
                    .getJSONArray("2016-08-20")
                    .toString()
            SPManager.setActivitiesByDay(this, 1, resultDay1)

            val resultDay2 = result.getJSONObject("data")
                    .getJSONArray("2016-08-21")
                    .toString()
            SPManager.setActivitiesByDay(this, 2, resultDay2)


            setupData()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    override fun onError(code: Int) {
        // TODO Hide progress bar
        Log.d(TAG, "DEBUG onError: $code")

        setupData()
    }

    companion object {


        private val TAG = "MainActivity"
    }
}
