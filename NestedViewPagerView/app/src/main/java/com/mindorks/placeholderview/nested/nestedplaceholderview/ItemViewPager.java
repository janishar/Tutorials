package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by janisharali on 18/01/17.
 */

@Layout(R.layout.item_viewpager)
public class ItemViewPager {

    private static int count = 1;

    @View(R.id.imageView)
    private ImageView mImageView;

    @View(R.id.textView)
    private TextView mTextView;

    @Position
    private int mPosition;

    public ItemViewPager() {
    }

    @Resolve
    private void onResolved() {
        if(count % 2 == 0){
            mImageView.setBackgroundColor(Color.BLACK);
        }else{
            mImageView.setBackgroundColor(Color.GRAY);
        }
        mTextView.setText(String.valueOf(mPosition));
    }
}
