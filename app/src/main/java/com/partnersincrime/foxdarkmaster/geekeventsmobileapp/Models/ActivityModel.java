package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models;

import android.graphics.Bitmap;

/**
 * Created by foxdarkmaster on 11-07-2016.
 */
public class ActivityModel {
    private int id;
    private String title;
    private String place;
    private String start;
    private Bitmap imageId;
    private String image;
    private String descr;



    public ActivityModel() { }

    public ActivityModel(String title, String place, String start, String image) {
        this.title = title;
        this.place = place;
        this.start = start;
        this.image = image;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImageId() { return imageId; }

    public void setImageId(Bitmap imageId) { this.imageId = imageId; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public boolean hasImages() {
        return this.image != null ? true : false;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
