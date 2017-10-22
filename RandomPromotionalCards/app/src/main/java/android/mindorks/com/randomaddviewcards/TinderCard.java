package android.mindorks.com.randomaddviewcards;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by janisharali on 19/08/16.
 */
@Layout(R.layout.tinder_card_view)
public class TinderCard {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private Profile mProfile;
    private Context mContext;
    private NormalCardSwipeCallback mCallback;

    public TinderCard(Context context, Profile profile, NormalCardSwipeCallback callback) {
        mContext = context;
        mProfile = profile;
        mCallback = callback;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mProfile.getImageUrl())
                .bitmapTransform(new RoundedCornersTransformation(mContext, Utils.dpToPx(7), 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(profileImageView);
        nameAgeTxt.setText(mProfile.getName() + ", " + mProfile.getAge());
        locationNameTxt.setText(mProfile.getLocation());
    }

    @Click(R.id.profileImageView)
    private void onClick() {
        Log.d("EVENT", "profileImageView click");
    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("EVENT", "onSwipedOut");
        if (mCallback != null) mCallback.onTinderCardSwiped();
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("EVENT", "onSwipedIn");
        if (mCallback != null) mCallback.onTinderCardSwiped();
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }

    protected interface NormalCardSwipeCallback {
        void onTinderCardSwiped();
    }
}
