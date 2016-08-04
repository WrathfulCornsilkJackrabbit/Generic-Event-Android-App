package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.ActivitiesAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.ArrayList;
import java.util.List;

import static com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils.decodeSampledBitmapFromResource;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityNextFragment extends Fragment {
    View rootView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ActivitiesAdapter mAdapter;

    List<ActivityModel> dataSet;

    public ActivityNextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activity_next_fragment, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_activities);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ActivitiesAdapter(dataSet);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void setData() {
        dataSet = new ArrayList<>();
        dataSet.add(new ActivityModel("Lorem ipsum dolor sit amet, agam melius 1", "Palco A", "10h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 2", "Palco B", "11h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 3", "Palco C", "12h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 4", "Palco D", "13h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 5", "Palco D", "13h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 6", "Palco D", "13h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 7", "Palco D", "13h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 8", "Palco D", "13h00", getTempImage()));
        dataSet.add(new ActivityModel("Test Title 9", "Palco D", "13h00", getTempImage()));
    }

    private Bitmap getTempImage() {
        return decodeSampledBitmapFromResource(getResources(), R.drawable.dummy, 800, 600);
    }
}
