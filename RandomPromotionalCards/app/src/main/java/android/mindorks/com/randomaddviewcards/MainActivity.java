package android.mindorks.com.randomaddviewcards;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        TinderCard.NormalCardSwipeCallback, AddViewCard.AddCardSwipeCallback {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private int mNormalCardsSwipedCounter = 0;
    private boolean mIsAddViewAdded;
    private int minNormalCardsToSwipeInCycle = 3;
    private int maxNormalCardsToSwipeInCycle = 7;
    private List<Profile> mProfileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        int bottomMargin = Utils.dpToPx(160);
        Point windowSize = Utils.getDisplaySize(getWindowManager());
        mSwipeView.getBuilder()
                .setDisplayViewCount(1)
                .setIsUndoEnabled(true)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeMaxChangeAngle(2f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));

        mProfileList = Utils.loadProfiles(getApplicationContext());

        if (mProfileList != null && mProfileList.size() > 0) {
            mSwipeView.addView(new TinderCard(mContext, mProfileList.remove(0), this));
        }

        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        findViewById(R.id.undoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.undoLastSwipe();
            }
        });


    }

    private boolean shouldAddNonNormalCard() {
        Random random = new Random(System.currentTimeMillis());
        int generatedNum = random.nextInt(2);
        Log.d("DEBUG", "shouldAddNonNormalCard: " + generatedNum);
        return generatedNum == 1;
    }

    @Override
    public void onTinderCardSwiped() {
        mNormalCardsSwipedCounter++;
        Log.d("DEBUG", "onTinderCardSwiped: " + mNormalCardsSwipedCounter);
        if (mProfileList.size() > 0) {

            if (mNormalCardsSwipedCounter <= minNormalCardsToSwipeInCycle) {
                mSwipeView.addView(new TinderCard(mContext, mProfileList.remove(0), this));

            } else if (mNormalCardsSwipedCounter <= maxNormalCardsToSwipeInCycle) {

                if (!mIsAddViewAdded && shouldAddNonNormalCard()) {
                    mSwipeView.addView(new AddViewCard(mContext,
                            "http://webneel.com/daily/sites/default/files/images/project/creative-advertisement%20(13).jpg",
                            this));
                    mIsAddViewAdded = true;

                } else {
                    mSwipeView.addView(new TinderCard(mContext, mProfileList.remove(0), this));
                }

                if (mNormalCardsSwipedCounter == maxNormalCardsToSwipeInCycle && !mIsAddViewAdded) {
                    mSwipeView.addView(new AddViewCard(mContext,
                            "http://webneel.com/daily/sites/default/files/images/project/creative-advertisement%20(13).jpg",
                            this));
                    mIsAddViewAdded = true;
                }

            } else {
                mSwipeView.addView(new TinderCard(mContext, mProfileList.remove(0), this));
                mNormalCardsSwipedCounter = 0;
                mIsAddViewAdded = false;
            }
        }
    }

    @Override
    public void onAddCardSwiped() {
        if (mProfileList.size() > 0) {
            mSwipeView.addView(new TinderCard(mContext, mProfileList.remove(0), this));
        }
    }
}
