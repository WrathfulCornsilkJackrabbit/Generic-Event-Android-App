package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityDoneFragment extends Fragment {

    public ActivityDoneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_done_fragment, container, false);
    }
}