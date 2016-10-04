package ali.jpostnetworking.test.jpostnetworking;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindorks.androidjpost.JPost;
import com.mindorks.jpost.core.OnMessage;
import com.mindorks.jpost.exceptions.JPostNotRunningException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by janisharali on 03/10/16.
 */

public class ApiHandler{

    public static String GIT_REPO_URL = "https://api.github.com/users/mralexgray/repos";

    private static final int API_CHANNEL_ID = 1000;
    private static ApiHandler apiHandler;

    public static void init(){
        apiHandler = new ApiHandler();
    }

    public ApiHandler() {
        try {
            JPost.getBroadcastCenter().createPrivateChannel(this, API_CHANNEL_ID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void doGitRepoGetApiCall(String url){
        try {
            JPost.getBroadcastCenter().broadcastAsync(apiHandler, API_CHANNEL_ID, url);
        }catch (JPostNotRunningException e){
            e.printStackTrace();
        }
    }

    @OnMessage(channelId = API_CHANNEL_ID)
    public void processGitRepoGet(String url) {
        try {
            URL obj = new URL(url);
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

                GitRepo[] gitRepoArray = gson.fromJson(response.toString(), GitRepo[].class);
                JPost.getBroadcastCenter().broadcastAsync(new GitRepoMsg(Arrays.asList(gitRepoArray)));
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (JPostNotRunningException e){
            e.printStackTrace();
        }
    }
}
