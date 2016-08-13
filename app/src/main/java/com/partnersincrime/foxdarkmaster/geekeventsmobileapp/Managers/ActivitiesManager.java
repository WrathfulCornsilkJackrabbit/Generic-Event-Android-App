package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by foxdarkmaster on 09-08-2016.
 */
public class ActivitiesManager {
    private static ActivitiesManager ourInstance = new ActivitiesManager();
    private LinkedHashMap<String, ArrayList<ActivityModel>> dayActivitiesMap = new LinkedHashMap<>();


    public static ActivitiesManager getInstance() {
        return ourInstance;
    }

    private ActivitiesManager() { }

    public void setDayActivitiesMap(ActivityModel[] activities) {
        dayActivitiesMap.clear();

        for (ActivityModel activity : activities) {
            dayActivitiesMap.put(activity.getTitle(), new ArrayList<ActivityModel>());
        }

        for (Map.Entry<String, ArrayList<ActivityModel>> entry : dayActivitiesMap.entrySet()) {
            String key = entry.getKey();

            for (ActivityModel activity : activities) {
                if (activity.getTitle().equals(key)) {
                    entry.getValue().add(activity);
                }
            }
        }
    }

    public LinkedHashMap<String, ArrayList<ActivityModel>> getDayActivitiesMap() {
        return dayActivitiesMap;
    }

    public ArrayList<ActivityModel> getAllActivitiesOfDay(String day) {
        if (dayActivitiesMap.containsValue(day)) {
            return dayActivitiesMap.get(day);
        }

        return null;
    }

    public ActivityModel getActivityById(int id){
        for (Map.Entry<String, ArrayList<ActivityModel>> entry : dayActivitiesMap.entrySet()) {
            for (ActivityModel activity : entry.getValue()) {
                if (activity.getId() == id) {
                    return activity;
                }
            }
        }

        return null;
    }
}
