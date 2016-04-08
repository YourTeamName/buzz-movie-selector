package edu.gatech.buzzmovieselector.controller.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// based on implementation here: http://www.androidhive
// .info/2015/09/android-material-design-working-with-tabs/

/**
 * View Pager Adapter allows fragments to be added in a tabbed format
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    /**
     * Super constructor
     * @param manager the fragment manager to create an adaptor for
     */
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Adds a fragment with an associated title
     *
     * @param fragment Fragment to add to viewpager
     * @param title    Title of the fragment tab
     */
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
