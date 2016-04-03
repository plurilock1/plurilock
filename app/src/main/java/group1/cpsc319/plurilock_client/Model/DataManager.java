package group1.cpsc319.plurilock_client.Model;

import android.view.MotionEvent;
import java.util.LinkedList;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.DataCollectionUtils.SocketClient;

public class DataManager {

    public static final String TAG = "DataManager";

    private static DataManager ourInstance = new DataManager();

    private static SocketClient socketClient = SocketClient.getInstance();

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

        JSONObject json = new JSONObject();

        try {
            json.put("btClientType", "mobile");
            json.put("btClientVersion", "1.0");
            json.put("userID", "CPSC319-Team-1");
            json.put("domain", "testDomain");

            JSONArray data = new JSONArray();
            for (MotionEvent e : touchDataCache) {
                JSONObject motionEventJson = new JSONObject();

                motionEventJson.put("x", e.getX());
                motionEventJson.put("y", e.getY());
                motionEventJson.put("xPrecision", e.getXPrecision());
                motionEventJson.put("yPrecision", e.getYPrecision());
                motionEventJson.put("abTime", e.getEventTime() - e.getDownTime());
                motionEventJson.put("touchType", e.getToolType(0));

                data.put(motionEventJson);
            }

            json.put("data", data);

            Log.i(TAG, "JSON: " + json.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketClient.sendMessage(json.toString())) {
            clearTouchDataCache();
        }
    }

    private void logTouchDataCacheInfo(String source) {
        Log.i(TAG, source);
        Log.i(TAG, "cacheCounter = " + cacheCounter);
        Log.i(TAG, "cacheSize = " + touchDataCache.size());
    }
}
