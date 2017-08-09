package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mindorks.placeholderview.PlaceHolderView;

/**
 * Created by janisharali on 19/01/17.
 */

public class CustomPlaceHolderView extends PlaceHolderView {

    public CustomPlaceHolderView(Context context) {
        super(context);
    }

    public CustomPlaceHolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPlaceHolderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }
}
