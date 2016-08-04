package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityCurrentFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityDoneFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityNextFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxdarkmaster on 29-07-2016.
 */
public class ActivityContainerActivities extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_swipe_with_tabs);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter pagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager(), ActivityContainerActivities.this);
        viewPager.setAdapter(pagerAdapter);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);

        adapter.addFragment(new ActivityNextFragment(), "Próximo");
        adapter.addFragment(new ActivityCurrentFragment(), "A Acontecer");
        adapter.addFragment(new ActivityDoneFragment(), "Terminados");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        Context context;

        public ViewPagerAdapter(FragmentManager manager, Context context) {
            super(manager);
            this.context = context;
        }

        @Override
        public int getCount() { return mFragmentList.size(); }

        @Override
        public Fragment getItem(int position) { return mFragmentList.get(position); }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) { return mFragmentTitleList.get(position); }
    }
}
