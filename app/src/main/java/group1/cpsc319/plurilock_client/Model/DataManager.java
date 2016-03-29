package group1.cpsc319.plurilock_client.Model;

import android.view.MotionEvent;
import java.util.LinkedList;
import android.util.Log;


/**
 * Created by Johnny on 2016-03-07.
 */
public class DataManager {

    public static final String TAG = "DataManager";

    private static DataManager ourInstance = new DataManager();

    // Save 10 events in cache before sending
    private static final int MAX_CACHE_COUNTER = 10;

    private static final int MAX_CACHE_SIZE = 1000;

    private int cacheCounter = 0;

    private LinkedList<MotionEvent> touchDataCache = new LinkedList<MotionEvent>();

    private DataManager() {}

    public static DataManager getInstance() {
        return ourInstance;
    }

    public synchronized void sendTouchData(MotionEvent me) {
        addToTouchDataCache(me);

        Log.i(TAG, "sendTouchData: " + me.toString());
        logTouchDataCacheInfo("sendTouchData");

        if (cacheCounter >= MAX_CACHE_COUNTER) {
            sendDataToServer();
        }
    }

    private void addToTouchDataCache(MotionEvent me) {

        while (touchDataCache.size() > MAX_CACHE_SIZE) {
            touchDataCache.poll();
        }
        touchDataCache.add(me);
        cacheCounter++;
        logTouchDataCacheInfo("addToTouchDataCache");
    }

    // Only call this if the data is successfully sent to the server!
    private void clearTouchDataCache() {
        touchDataCache.clear();
        cacheCounter = 0;
        logTouchDataCacheInfo("clearTouchDataCache");
    }

    private void sendDataToServer() {
        logTouchDataCacheInfo("sendDataToServer");
        // TODO: Use James's send-to-server code when available
        // If possible, use a callback to indicate whether data was successfully sent
        clearTouchDataCache();
    }

    private void logTouchDataCacheInfo(String source) {
        Log.i(TAG, source);
        Log.i(TAG, "cacheCounter = " + cacheCounter);
        Log.i(TAG, "cacheSize = " + touchDataCache.size());
    }
}
