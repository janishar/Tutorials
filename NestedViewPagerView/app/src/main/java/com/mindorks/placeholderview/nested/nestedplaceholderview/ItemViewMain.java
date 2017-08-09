package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.*;
import android.util.Log;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janisharali on 18/01/17.
 */

@Layout(R.layout.item_view_main)
public class ItemViewMain {

    private static int count = 1;
    private static final String TAG = "ItemViewMain";

    @View(R.id.viewPager)
    private ViewPager viewPager;

    private FragmentManager mFragmentManager;

    public ItemViewMain(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @Resolve
    private void onResolved() {
        PagerAdapter pagerAdapter;
        if(viewPager.getAdapter() == null){
            pagerAdapter = new PagerAdapter(mFragmentManager);
            viewPager.setAdapter(pagerAdapter);
        }else{
            pagerAdapter = (PagerAdapter)viewPager.getAdapter();
        }
        pagerAdapter.clear();
        pagerAdapter.notifyDataSetChanged();
        pagerAdapter.addFragment(PagerFragment.newInstance(), String.valueOf(1));
        pagerAdapter.addFragment(PagerFragment.newInstance(), String.valueOf(2));
        pagerAdapter.addFragment(PagerFragment.newInstance(), String.valueOf(3));
        pagerAdapter.addFragment(PagerFragment.newInstance(), String.valueOf(4));
        pagerAdapter.addFragment(PagerFragment.newInstance(), String.valueOf(5));
        pagerAdapter.notifyDataSetChanged();
        count++;
        if(count % 2 == 0){
            viewPager.setBackgroundColor(Color.BLUE);
        }else{
            viewPager.setBackgroundColor(Color.GREEN);
        }

    }
}
