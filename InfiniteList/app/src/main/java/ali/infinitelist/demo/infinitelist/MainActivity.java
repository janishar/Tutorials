package ali.infinitelist.demo.infinitelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private InfinitePlaceHolderView mLoadMoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadMoreView = (InfinitePlaceHolderView)findViewById(R.id.loadMoreView);
        setupView();
    }

    private void setupView(){

        final List<InfiniteFeedInfo> feedList = Utils.loadInfiniteFeeds(this.getApplicationContext());
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemView(this.getApplicationContext(), feedList.get(i)));
        }
        mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedList.clear();
                mLoadMoreView.removeAllViews();
            }
        });
    }

}
