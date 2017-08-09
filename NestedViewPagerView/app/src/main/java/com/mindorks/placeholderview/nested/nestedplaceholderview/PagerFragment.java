package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mindorks.placeholderview.PlaceHolderView;

/**
 * Created by janisharali on 18/01/17.
 */

public class PagerFragment extends Fragment{

    private static final String TAG = "PagerFragment";

    public static PagerFragment newInstance() {
        PagerFragment fragment = new PagerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page, container, false);
        PlaceHolderView holderView = (PlaceHolderView)rootView.findViewById(R.id.viewHolder);
        holderView
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager())
                .addView(new ItemViewPager());
        return rootView;
    }
}

