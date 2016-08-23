package test.mindorks.gallery.gallerytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.mindorks.placeholderview.PlaceHolderView;

public class MainActivity extends AppCompatActivity {

    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGalleryView = (PlaceHolderView)findViewById(R.id.galleryView);
        mGalleryView
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_1)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_2)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_3)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_4)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_5)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_6)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_7)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_8)))
                .addView(new GalleryItem(getResources().getDrawable(R.drawable.img_9)));
    }
}
