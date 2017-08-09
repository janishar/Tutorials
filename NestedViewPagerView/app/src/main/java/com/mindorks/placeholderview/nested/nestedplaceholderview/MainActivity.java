package com.mindorks.placeholderview.nested.nestedplaceholderview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mindorks.placeholderview.PlaceHolderView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceHolderView holderView = (PlaceHolderView)findViewById(R.id.viewHolder);
        holderView
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()))
                .addView(new ItemViewMain(getSupportFragmentManager()));
    }
}
