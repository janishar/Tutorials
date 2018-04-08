package tinderswipe.swipe.mindorks.demo

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.annotations.*
import com.mindorks.placeholderview.annotations.swipe.*
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlin.math.sqrt
import kotlin.tinderswipe.swipe.mindorks.demo.R

@Layout(R.layout.tinder_card_view)
class TinderCard(private val context: Context,
                 private val profile: Profile,
                 private val cardViewHolderSize: Point,
                 private val callback: Callback) {

    @View(R.id.profileImageView)
    lateinit var profileImageView: ImageView

    @View(R.id.nameAgeTxt)
    lateinit var nameAgeTxt: TextView

    @View(R.id.locationNameTxt)
    lateinit var locationNameTxt: TextView

    @SwipeView
    lateinit var swipeView: android.view.View

    @JvmField
    @Position
    var position: Int = 0;

    @Resolve
    fun onResolved() {
        Glide.with(context).load(profile.imageUrl).bitmapTransform(
                RoundedCornersTransformation(
                        context,
                        Utils.dpToPx(7),
                        0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(profileImageView)
        nameAgeTxt.text = "${profile.name},  ${profile.age}"
        locationNameTxt.text = profile.location
        swipeView.alpha = 1f
    }

    @Click(R.id.profileImageView)
    fun onClick() {
        Log.d("EVENT", "profileImageView click")
    }

    @SwipeOutDirectional
    fun onSwipeOutDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeOutDirectional " + direction.name)
        if (direction.direction == SwipeDirection.TOP.direction) {
            callback.onSwipeUp()
        }
    }

    @SwipeCancelState
    fun onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState")
        swipeView.alpha = 1f
    }

    @SwipeInDirectional
    fun onSwipeInDirectional(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipeInDirectional " + direction.name)
    }

    @SwipingDirection
    fun onSwipingDirection(direction: SwipeDirection) {
        Log.d("DEBUG", "SwipingDirection " + direction.name)
    }

    @SwipeTouch
    fun onSwipeTouch(xStart: Float, yStart: Float, xCurrent: Float, yCurrent: Float) {

        val cardHolderDiagonalLength =
                sqrt(Math.pow(cardViewHolderSize.x.toDouble(), 2.0)
                        + (Math.pow(cardViewHolderSize.y.toDouble(), 2.0)))
        val distance = sqrt(Math.pow(xCurrent.toDouble() - xStart.toDouble(), 2.0)
                + (Math.pow(yCurrent.toDouble() - yStart, 2.0)))

        val alpha = 1 - distance / cardHolderDiagonalLength

        Log.d("DEBUG", "onSwipeTouch "
                + " xStart : " + xStart
                + " yStart : " + yStart
                + " xCurrent : " + xCurrent
                + " yCurrent : " + yCurrent
                + " distance : " + distance
                + " TotalLength : " + cardHolderDiagonalLength
                + " alpha : " + alpha
        )

        swipeView.alpha = alpha.toFloat();
    }

    interface Callback {
        fun onSwipeUp()
    }
}