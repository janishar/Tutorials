package ali.nestedplaceholderview.demo.nestedplaceholderview;

import android.widget.TextView;

import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by janisharali on 11/10/16.
 */
//@Animate(Animation.CARD_TOP_IN_DESC)
@Layout(R.layout.news_grid_item)
public class GridItemView {

    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.pubDateTxt)
    private TextView pubDateTxt;

    @View(R.id.newsTxt)
    private TextView newsTxt;

    private int state;

    private GoogleNews.entry googleNewsEntry;

    public GridItemView(GoogleNews.entry googleNewsEntry, int state) {
        this.googleNewsEntry = googleNewsEntry;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Resolve
    public void onResolved(){
        titleTxt.setText(String.valueOf(googleNewsEntry.getTitle()));
        pubDateTxt.setText(googleNewsEntry.getPublishedDate());
        newsTxt.setText(googleNewsEntry.getContentSnippet());
    }
}
