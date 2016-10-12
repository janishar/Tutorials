package ali.nestedplaceholderview.demo.nestedplaceholderview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mindorks.placeholderview.PlaceHolderView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceHolderView repoListView = (PlaceHolderView) findViewById(R.id.repoListView);
        repoListView
                .addView(new RowListView(repoListView))
                .addView(new RowListView2(repoListView))
                .addView(new RowListView(repoListView))
                .addView(new RowListView2(repoListView));
    }
}
