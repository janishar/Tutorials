package ali.jpostnetworking.test.jpostnetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.mindorks.androidjpost.JPost;
import com.mindorks.androidjpost.droid.OnUiThread;
import com.mindorks.jpost.core.OnMessage;
import com.mindorks.jpost.exceptions.JPostNotRunningException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView repoListView;
    private RepoListAdapter repoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repoListView = (ListView) findViewById(R.id.repoListView);
        repoListAdapter = new RepoListAdapter(getApplicationContext(), new ArrayList<GitRepo>());
        repoListView.setAdapter(repoListAdapter);
        try {
            JPost.getBroadcastCenter().addSubscriber(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        ApiHandler.doGitRepoGetApiCall(ApiHandler.GIT_REPO_URL);
    }

    @OnUiThread
    @OnMessage
    private void onGitRepoList(GitRepoMsg msg){
        if(msg.getGitRepoList() != null) {
            repoListAdapter.setGitRepoList(msg.getGitRepoList());
        }
    }
}
