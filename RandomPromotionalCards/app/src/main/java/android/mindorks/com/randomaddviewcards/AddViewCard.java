package android.mindorks.com.randomaddviewcards;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by janisharali on 19/08/16.
 */
@Layout(R.layout.add_card_view)
public class AddViewCard {

    @View(R.id.addImageView)
    private ImageView addImageView;

    private Context mContext;
    private String mImageUrl;
    private AddCardSwipeCallback mCallback;

    public AddViewCard(Context context, String imageUrl, AddCardSwipeCallback callback) {
        mContext = context;
        mImageUrl = imageUrl;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mImageUrl)
                .bitmapTransform(new RoundedCornersTransformation(mContext, Utils.dpToPx(7), 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(addImageView);
    }

    @SwipeOut
    private void onSwipedOut() {
        if (mCallback != null) mCallback.onAddCardSwiped();
    }

    @SwipeIn
    private void onSwipeIn() {
        if (mCallback != null) mCallback.onAddCardSwiped();
    }

    protected interface AddCardSwipeCallback {
        void onAddCardSwiped();
    }
}
