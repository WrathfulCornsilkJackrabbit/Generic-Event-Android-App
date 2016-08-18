package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by foxdarkmaster on 01-07-2016.
 */
public class MapActivity extends BaseActivity {
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setActionBar();
        setViews();
        setData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_map;
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.activity_map_title));
    }

    private void setData() {
        Drawable bitmap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bitmap = getDrawable(R.drawable.current_map);
        } else {
            bitmap = getResources().getDrawable(R.drawable.current_map);
        }

        mImageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImageView);
    }

    private void setViews() {
        mImageView = (ImageView) findViewById(R.id.map_image_view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mAttacher.cleanup();
    }
}

