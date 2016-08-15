package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    private Toolbar toolbar;
    private ActivityModel activity;

    protected TextView mTitleView;
    protected TextView mTimeStartView;
    protected TextView mLocationView;
    protected ImageView mImageView;
    protected TextView mDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setupToolbar();
        setViews();
        setData();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setData() {
        String imageUrl;
        activity = ActivitiesManager.getInstance().getActivityToHold();

        Log.d(TAG, "DEBUG test selected values");
        Log.d(TAG, "DEBUG getTitle: " + activity.getTitle());
        Log.d(TAG, "DEBUG getStart: " + activity.getStart());
        Log.d(TAG, "DEBUG getPlace: " + activity.getPlace());
        Log.d(TAG, "DEBUG getDescr: " + activity.getDescr());
        Log.d(TAG, "DEBUG getImage: " + activity.getImage());

        mTitleView.setText(activity.getTitle());
        mTimeStartView.setText(activity.getStart());
        mLocationView.setText(activity.getPlace());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mDescriptionView.setText(Html.fromHtml(activity.getDescr(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            mDescriptionView.setText(Html.fromHtml(activity.getDescr()));
        }

        if (activity.hasImages()) {
            imageUrl = Utils.getUrlForImage(activity.getImage());
        } else {
            imageUrl = "http://dummyimage.com/vga";
        }

        Glide
            .with(this)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(mImageView);
        //mImageView.setImage;
    }

    private void setViews() {
        mTitleView = (TextView) findViewById(R.id.detail_card_title);
        mTimeStartView = (TextView) findViewById(R.id.detail_card_hour_start);
        mLocationView = (TextView) findViewById(R.id.detail_card_location);
        mImageView = (ImageView) findViewById(R.id.detail_card_image);

        mDescriptionView = (TextView) findViewById(R.id.detail_card_description);
    }
}
