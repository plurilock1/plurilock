package group1.cpsc319.plurilock_client.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.DataCollectionUtils.SocketClient;

public class DataManager {

    public static final String TAG = "DataManager";
    public static final String TOUCH_DATA = "touch data";
    public static final String KEY_DATA = "key data";

    // Singleton Instance
    private static DataManager ourInstance = new DataManager();

    private static SocketClient socketClient = SocketClient.getInstance();

    // Save 10 events in cache before sending
    private static final int MAX_CACHE_COUNTER = 10;

    private static final int MAX_CACHE_SIZE = 1000;

    private int dataCacheCounter = 0;
    private JSONArray dataCache = new JSONArray();

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

        if (dataCacheCounter >= MAX_CACHE_COUNTER) {
            sendDataToServer(cacheTag);
        }
    }

    private synchronized void addToCache(JSONObject obj, String cacheTag) throws JSONException {
        while (dataCache.length() > MAX_CACHE_SIZE) {
            dataCache.remove(0);
        }
        dataCache.put(obj);
        dataCacheCounter++;
        logCacheInfo("addToCache (" + cacheTag + ")", cacheTag);
    }

    // Only call this if the data is successfully sent to the server!
    private synchronized void clearCache(String cacheTag) {
        dataCache = new JSONArray();
        dataCacheCounter = 0;
        logCacheInfo("clearCache (" + cacheTag + ")", cacheTag);
    }

    private synchronized void sendDataToServer(String cacheTag) {
        logCacheInfo("sendDataToServer", cacheTag);
        JSONObject json = new JSONObject();

        try {
            json.put("btClientType", "mobile");
            json.put("btClientVersion", "1.0");
            json.put("userID", "CPSC319-Team-1");
            json.put("domain", "testDomain");
            json.put("data", dataCache);

            Log.i(TAG, "JSON: " + json.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketClient.sendMessage(json.toString())) {
            clearCache(cacheTag);
        }
    }

    private synchronized void logCacheInfo(String source, String cacheTag) {
        Log.i(TAG, source);
        Log.i(TAG, "dataCacheCounter = " + dataCacheCounter);
        Log.i(TAG, "dataCacheSize = " + dataCache.length());
    }
}
