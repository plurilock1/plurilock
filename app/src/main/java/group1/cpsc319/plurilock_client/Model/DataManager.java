package group1.cpsc319.plurilock_client.Model;

import android.view.MotionEvent;
import java.util.LinkedList;
import android.util.Log;
import net.sf.ehcache.*;


/**
 * Created by Johnny on 2016-03-07.
 */
public class DataManager {

    public static final String TAG = "DataManager";

    private static DataManager ourInstance = new DataManager();

    // Save 10 events in cache before sending
    private static final int MAX_CACHE_COUNTER = 10;

    private Cache touchDataCache = CacheManager.getInstance().getCache("touchDataCache");

    private DataManager() {}

    public static DataManager getInstance() {
        return ourInstance;
    }

    public void sendTouchData(MotionEvent me) {
        addToTouchDataCache(me);
        int cacheCounter = touchDataCache.getKeysNoDuplicateCheck().size();
        Log.i(TAG, "sendTouchData: " + me.toString());
        Log.i(TAG, "cacheCounter = " + cacheCounter);
        if (cacheCounter >= MAX_CACHE_COUNTER) {
            sendDataToServer();
        }
    }

    private void addToTouchDataCache(MotionEvent me) {

        touchDataCache.put(new Element(me.getEventTime(), me));
        Log.i(TAG, "addToTouchDataCache: " + me.toString());
        Log.i(TAG, "cacheCounter = " + touchDataCache.getKeysNoDuplicateCheck().size());
    }

    // Only call this if the data is successfully sent to the server!
    private void clearTouchDataCache() {
        touchDataCache.removeAll();
        Log.i(TAG, "clearTouchDataCache");
        Log.i(TAG, "cacheCounter = " + touchDataCache.getKeysNoDuplicateCheck().size());
    }

    private void sendDataToServer() {
        Log.i(TAG, "sendDataToServer");
        // TODO: Use James's send-to-server code when available
        // If possible, use a callback to indicate whether data was successfully sent
        clearTouchDataCache();
    }
}
