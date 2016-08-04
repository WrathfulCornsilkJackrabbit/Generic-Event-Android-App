package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models;

import android.graphics.Bitmap;

/**
 * Created by foxdarkmaster on 11-07-2016.
 */
public class ActivityModel {
    private String title;
    private String location;
    private String time;
    private Bitmap imageId;

    ActivityModel() { }

    public ActivityModel(String title, String location, String time, Bitmap imageId) {
        this.title = title;
        this.location = location;
        this.time = time;
        this.imageId = imageId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageId() { return imageId; }

    public void setImageId(Bitmap imageId) { this.imageId = imageId; }
}
