package ali.nestedplaceholderview.demo.nestedplaceholderview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by janisharali on 11/10/16.
 */
//@Animate(Animation.CARD_LEFT_IN_DESC)
@Layout(R.layout.news_row_list)
public class RowListView {

    @View(R.id.mainListView)
    private PlaceHolderView mainListView;

    private final int DO_NOTHING = 0;
    private final int ADD_VIEW = 1;
    private final int UPDATE_VIEW = 2;

    private HashMap<GridItemView, Integer> listItemGoogleNewsMap;
    private PlaceHolderView newsListView;

    public RowListView(PlaceHolderView newsListView) {
        this.newsListView = newsListView;

        this.listItemGoogleNewsMap = new HashMap<>();
        new NetworkCall();
    }

    @Resolve
    public void onResolved(){
        if(mainListView.getViewResolverCount() == 0) {
            mainListView.getBuilder()
                    .setHasFixedSize(false)
                    .setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        }
        for(GridItemView gridItemView : listItemGoogleNewsMap.keySet()){
            switch (listItemGoogleNewsMap.get(gridItemView)){
                case DO_NOTHING:
                    break;
                case ADD_VIEW:
                    mainListView.addView(gridItemView);
                    listItemGoogleNewsMap.put(gridItemView, DO_NOTHING);
                    break;
                case UPDATE_VIEW:
                    mainListView.refreshView(gridItemView);
                    listItemGoogleNewsMap.put(gridItemView, DO_NOTHING);
                    break;
            }
        }
    }

    private class NetworkCall implements Runnable{

        public NetworkCall() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                URL obj = new URL(GoogleNews.URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                Log.d("Debug", "Response Code : " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Log.d("Debug", "Response : " + response);
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    final GoogleNews googleNews = gson.fromJson(response.toString(), GoogleNews.class);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            for(GoogleNews.entry entry : googleNews.getResponse().getFeedData().getEntryList()){
                                listItemGoogleNewsMap.put(new GridItemView(entry), ADD_VIEW);
                            }
                            newsListView.refreshView(RowListView.this);
                        }
                    });

                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
