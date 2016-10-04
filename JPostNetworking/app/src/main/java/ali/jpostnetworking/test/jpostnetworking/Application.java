package ali.jpostnetworking.test.jpostnetworking;

/**
 * Created by janisharali on 03/10/16.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiHandler.init();
    }
}
