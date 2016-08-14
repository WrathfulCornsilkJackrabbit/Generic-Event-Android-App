package com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityCurrentFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityDoneFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.Fragments.ActivityNextFragment;
import com.partnersincrime.foxdarkmaster.geekeventsmobileapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by foxdarkmaster on 29-07-2016.
 */
public class ActivityContainerActivities extends BaseActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int getLayoutResource() {
        return R.layout.activities_swipe_with_tabs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_container, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities_swipe_with_tabs);

        setActionBar();
        setViews();
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void setViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter pagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager(), ActivityContainerActivities.this);
        viewPager.setAdapter(pagerAdapter);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);

        adapter.addFragment(new ActivityNextFragment(), getResources().getString(R.string.fragment_title_next));
        adapter.addFragment(new ActivityCurrentFragment(), getResources().getString(R.string.fragment_title_current));
        adapter.addFragment(new ActivityDoneFragment(), getResources().getString(R.string.fragment_title_done));

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
