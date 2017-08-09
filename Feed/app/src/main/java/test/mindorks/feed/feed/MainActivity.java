package test.mindorks.feed.feed;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.mindorks.placeholderview.PlaceHolderView;

public class MainActivity extends AppCompatActivity {

    private ExpandablePlaceHolderView mExpandableView;
    private Context mContext;
    private ExpandablePlaceHolderView.OnScrollListener mOnScrollListener;
    private boolean mIsLoadingMore = false;
    private boolean mNoMoreToLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        mExpandableView = (ExpandablePlaceHolderView)findViewById(R.id.expandableView);

        for (Feed feed : Utils.loadFeeds(this.getApplicationContext())) {
            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
            for (Info info : feed.getInfoList()) {
                mExpandableView.addView(new InfoView(mContext, info));
            }
        }
//        setLoadMoreListener(mExpandableView);
    }

    private void setLoadMoreListener(ExpandablePlaceHolderView expandableView) {
        mOnScrollListener =
                new PlaceHolderView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        ExpandablePlaceHolderView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if(layoutManager instanceof LinearLayoutManager) {
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                            int totalItemCount = linearLayoutManager.getItemCount();
                            int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                            if (!mIsLoadingMore
                                    && !mNoMoreToLoad
                                    && totalItemCount > 0
                                    && totalItemCount == lastVisibleItem + 1) {
                                mIsLoadingMore = true;
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {

                                        // do api call to fetch data

                                        // example of loading from file:
                                        for(Feed feed : Utils.loadFeeds(getApplicationContext())){
                                            mExpandableView.addView(new HeadingView(mContext, feed.getHeading()));
                                            for(Info info : feed.getInfoList()){
                                                mExpandableView.addView(new InfoView(mContext, info));
                                            }
                                        }
                                        mIsLoadingMore = false;
                                    }
                                });
                            }
                        }
                    }
                };
        expandableView.addOnScrollListener(mOnScrollListener);
    }
}