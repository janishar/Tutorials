package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janisharali on 18/01/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> fragmentTitleList;

    public PagerAdapter(FragmentManager manager) {
        super(manager);
        this.fragmentList = new ArrayList<>();
        this.fragmentTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    public void clear(){
        fragmentList.clear();
        fragmentTitleList.clear();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

}
