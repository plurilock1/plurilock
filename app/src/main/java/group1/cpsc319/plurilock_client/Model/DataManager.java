package group1.cpsc319.plurilock_client.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import group1.cpsc319.plurilock_client.DataCollectionUtils.SocketClient;

public class DataManager {

    public static final String TAG = "DataManager";

    // Singleton Instance
    private static DataManager ourInstance = new DataManager();

    private static SocketClient socketClient = SocketClient.getInstance();

    private static final Map<String, String> APP_DATA_JSON;

    // Save 10 events in cache before sending
    private static final int MAX_CACHE_COUNTER = 10;

    private static final int MAX_CACHE_SIZE = 1000;

    private int dataCacheCounter = 0;
    private JSONArray dataCache = new JSONArray();

    static {
        APP_DATA_JSON = new HashMap<String, String>();
        APP_DATA_JSON.put("btClientType", "mobile");
        APP_DATA_JSON.put("btClientVersion", "1.0");
        APP_DATA_JSON.put("userID", "CPSC319-Team-1");
        APP_DATA_JSON.put("domain", "testDomain");
    }

    private DataManager() {}

    public static DataManager getInstance() {
        return ourInstance;
    }

    public synchronized void sendData(JSONObject obj) {
        try {
            addToCache(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "sendData: " + obj.toString());
        logCacheInfo("sendData");

        if (dataCacheCounter >= MAX_CACHE_COUNTER) {
            sendDataToServer();
        }
    }

    private synchronized void addToCache(JSONObject obj) throws JSONException {
        while (dataCache.length() > MAX_CACHE_SIZE) {
            dataCache.remove(0);
        }
        dataCache.put(obj);
        dataCacheCounter++;
        logCacheInfo("addToCache");
    }

    // Only call this if the data is successfully sent to the server!
    private synchronized void clearCache() {
        dataCache = new JSONArray();
        dataCacheCounter = 0;
        logCacheInfo("clearCache");
    }

    private synchronized void sendDataToServer() {
        logCacheInfo("sendDataToServer");
        JSONObject json = new JSONObject(APP_DATA_JSON);

        try {
            json.put("data", dataCache);

            Log.i(TAG, "JSON: " + json.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (socketClient.sendMessage(json.toString())) {
            clearCache();
        }
    }

    private synchronized void logCacheInfo(String source) {
        Log.i(TAG, source);
        Log.i(TAG, "dataCacheCounter = " + dataCacheCounter);
        Log.i(TAG, "dataCacheSize = " + dataCache.length());
    }
}
