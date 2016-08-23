package test.mindorks.gallery.gallerytest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by janisharali on 19/08/16.
 */
@Animate(Animation.CARD_LEFT_IN_DESC)
@NonReusable
@Layout(R.layout.gallery_item)
public class GalleryItem {

    @View(R.id.imageView)
    private ImageView imageView;

    private Drawable mDrawable;

    public GalleryItem(Drawable drawable) {
        mDrawable = drawable;
    }

    @Resolve
    private void onResolved() {
        imageView.setImageDrawable(mDrawable);
    }
}
