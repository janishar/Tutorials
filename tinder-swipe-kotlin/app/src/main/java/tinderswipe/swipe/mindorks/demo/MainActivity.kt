package tinderswipe.swipe.mindorks.demo

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast
import com.mindorks.placeholderview.SwipeDecor
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.tinderswipe.swipe.mindorks.demo.R

class MainActivity : AppCompatActivity(), TinderCard.Callback {

    companion object {
        val TAG = "MainActivity"
    }

    private val animationDuration = 300
    private var isToUndo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMargin = Utils.dpToPx(160)
        val windowSize = Utils.getDisplaySize(windowManager)
        swipeView!!.builder
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setSwipeVerticalThreshold(Utils.dpToPx(50))
                .setSwipeHorizontalThreshold(Utils.dpToPx(50))
                .setHeightSwipeDistFactor(10f)
                .setWidthSwipeDistFactor(5f)
                .setSwipeDecor(SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setSwipeAnimTime(animationDuration)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view))


        val cardViewHolderSize = Point(windowSize.x, windowSize.y - bottomMargin)

        for (profile in Utils.loadProfiles(applicationContext)) {
            swipeView!!.addView(TinderCard(applicationContext, profile, cardViewHolderSize, this))
        }

        rejectBtn.setOnClickListener({ swipeView!!.doSwipe(false) })

        acceptBtn.setOnClickListener({ swipeView!!.doSwipe(true) })

        undoBtn.setOnClickListener({ swipeView!!.undoLastSwipe() })

        swipeView!!.addItemRemoveListener {
            if (isToUndo) {
                isToUndo = false
                swipeView!!.undoLastSwipe()
            }
        }
    }


    override fun onSwipeUp() {
        Toast.makeText(this, "SUPER LIKE! Show any dialog here.", Toast.LENGTH_SHORT).show()
        isToUndo = true
    }
}
