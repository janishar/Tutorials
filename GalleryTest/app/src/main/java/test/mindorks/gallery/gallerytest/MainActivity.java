package test.mindorks.gallery.gallerytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import test.mindorks.gallery.gallerytest.gallery.ImageTypeBig;
import test.mindorks.gallery.gallerytest.gallery.ImageTypeSmallList;

public class MainActivity extends AppCompatActivity {

    private PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGalleryView = (PlaceHolderView)findViewById(R.id.galleryView);
        setupGallery();
    }

    private void setupGallery(){

        List<Image> imageList = Utils.loadImages(this.getApplicationContext());
        List<Image> newImageList = new ArrayList<>();
        for (int i = 0; i <  (imageList.size() > 10 ? 10 : imageList.size()); i++) {
            newImageList.add(imageList.get(i));
        }
        mGalleryView.addView(new ImageTypeSmallList(this.getApplicationContext(), newImageList));

        for (int i = imageList.size() - 1; i >= 0; i--) {
            mGalleryView.addView(new ImageTypeBig(this.getApplicationContext(), mGalleryView, imageList.get(i).getImageUrl()));
        }

        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGalleryView.removeAllViews();
            }
        });
    }
}
