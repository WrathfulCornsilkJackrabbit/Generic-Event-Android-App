package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Adapters.ActivitiesAdapter;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Managers.ActivitiesManager;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Models.ActivityModel;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.Arrays;

import static com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Utilities.Utils.decodeSampledBitmapFromResource;

/**
 * A placeholder fragment containing a simple view.
 */
public class ActivityNextFragment extends Fragment {
    private static final String TAG = "ActivityNextFragment";
    View rootView;
    TextView mEmptyView;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ActivitiesAdapter mAdapter;
    ActivityModel currentDay[];


    public ActivityNextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setData();
    }

    private void setData() {
        currentDay = ActivitiesManager.getInstance().getNextActivitiesData();

        /*
        // Test Activities
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
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activity, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_activities);
        mEmptyView = (TextView) rootView.findViewById(R.id.empty_view);

        if (currentDay == null) {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);

            //mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_activities);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(getActivity());
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLayoutManager);


            mAdapter = new ActivitiesAdapter(Arrays.asList(currentDay));
            mRecyclerView.setAdapter(mAdapter);
        }

        return rootView;
    }

    private Bitmap getTempImage() {
        return decodeSampledBitmapFromResource(getResources(), R.drawable.dummy, 400, 200);
    }
}
