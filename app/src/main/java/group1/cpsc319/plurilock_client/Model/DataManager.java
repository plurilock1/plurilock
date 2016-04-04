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

    public static final String TOUCH_DATA_CACHE = "touch data";
    public static final String KEY_DATA_CACHE = "key data";

    private static DataManager ourInstance = new DataManager();

    private static SocketClient socketClient = SocketClient.getInstance();

    // Save 10 events in cache before sending
    private static final int MAX_CACHE_COUNTER = 10;

    private static final int MAX_CACHE_SIZE = 1000;

    private int touchDataCacheCounter = 0;
    private JSONArray touchDataCache = new JSONArray();

    private int keyDataCacheCounter = 0;
    private JSONArray keyDataCache = new JSONArray();

    private JSONArray currCache;

    private DataManager() {}

    public static DataManager getInstance() {
        return ourInstance;
    }

    public synchronized void sendData(JSONObject obj, String cacheTag) {

        try {
            addToCache(obj, cacheTag);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "sendData (" + cacheTag + "): " + obj.toString());
        logCacheInfo("sendData (" + cacheTag + ")", cacheTag);

        int cacheCounter;
        switch (cacheTag) {
            case TOUCH_DATA_CACHE:
                cacheCounter = touchDataCacheCounter;
                break;
            case KEY_DATA_CACHE:
                cacheCounter = keyDataCacheCounter;
                break;
            default:
                throw new IllegalArgumentException();
        }

        if (cacheCounter >= MAX_CACHE_COUNTER) {
            sendDataToServer(cacheTag);
        }
    }

    private synchronized void addToCache(JSONObject obj, String cacheTag) throws JSONException {

        setCurrCache(cacheTag);

        while (currCache.length() > MAX_CACHE_SIZE) {
            currCache.remove(0);
        }
        currCache.put(obj);
        switch (cacheTag) {
            case TOUCH_DATA_CACHE:
                touchDataCacheCounter++;
                break;
            case KEY_DATA_CACHE:
                keyDataCacheCounter++;
                break;
            default:
                throw new IllegalArgumentException();
        }
        logCacheInfo("addToCache (" + cacheTag + ")", cacheTag);
    }

    // Only call this if the data is successfully sent to the server!
    private synchronized void clearCache(String cacheTag) {

        switch (cacheTag) {
            case TOUCH_DATA_CACHE:
                touchDataCache = new JSONArray();
                touchDataCacheCounter = 0;
                break;
            case KEY_DATA_CACHE:
                keyDataCache = new JSONArray();
                keyDataCacheCounter = 0;
                break;
            default:
                throw new IllegalArgumentException();
        }
        logCacheInfo("clearCache (" + cacheTag + ")", cacheTag);
    }

    private synchronized void sendDataToServer(String cacheTag) {
        logCacheInfo("sendDataToServer", cacheTag);

        setCurrCache(cacheTag);

        JSONObject json = new JSONObject();

        try {
            json.put("btClientType", "mobile");
            json.put("btClientVersion", "1.0");
            json.put("userID", "CPSC319-Team-1");
            json.put("domain", "testDomain");

            json.put("data", currCache);

            Log.i(TAG, "JSON: " + json.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketClient.sendMessage(json.toString())) {
            clearCache(cacheTag);
        }
    }

    private synchronized void setCurrCache(String cacheTag) {
        switch (cacheTag) {
            case TOUCH_DATA_CACHE:
                currCache = touchDataCache;
                break;
            case KEY_DATA_CACHE:
                currCache = keyDataCache;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private synchronized void logCacheInfo(String source, String cacheTag) {
        Log.i(TAG, source);
        switch (cacheTag) {
            case TOUCH_DATA_CACHE:
                Log.i(TAG, "touchDataCacheCounter = " + touchDataCacheCounter);
                Log.i(TAG, "touchDataCacheSize = " + touchDataCache.length());
                break;
            case KEY_DATA_CACHE:
                Log.i(TAG, "keyDataCacheCounter = " + keyDataCacheCounter);
                Log.i(TAG, "keyDataCacheSize = " + keyDataCache.length());
                break;
            default:
                throw new IllegalArgumentException();
        }


    }
}
