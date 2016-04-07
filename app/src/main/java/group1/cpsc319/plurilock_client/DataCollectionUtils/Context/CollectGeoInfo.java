package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import group1.cpsc319.plurilock_client.Model.DataManager;

/**
 *  Helper class for collecting device geographic data
 *
 * Created by Junoh on 2/27/2016.
 */
public class CollectGeoInfo implements CollectDeviceContextData {

    private static DataManager dataManager = DataManager.getInstance();
    private String language;
    private TimeZone timeZone;
    private long time;
    private String country;
    private double latitude;
    private double longitude;

    public CollectGeoInfo(Activity activity, GPSTracker gpsTracker) {
        this.language = Locale.getDefault().getISO3Language();
        Calendar cal = Calendar.getInstance();
        this.timeZone = cal.getTimeZone();
        this.time = cal.getTimeInMillis();
        this.country = Locale.getDefault().getISO3Country();

        if (gpsTracker != null) {
            this.latitude = gpsTracker.getLatitude();
            this.longitude = gpsTracker.getLongitude();
        }
    }

    public void collectDeviceInfo() {
        JSONObject geoInfo = new JSONObject();
        try {
            geoInfo.put("Language", this.getLanguage());
            geoInfo.put("Timezone", this.getTimeZone().getID());
            geoInfo.put("Time", this.getTimeInMils());
            geoInfo.put("CountryCode", this.getCountry());
            geoInfo.put("Latitude", this.getLatitude());
            geoInfo.put("Longitude", this.getLongitude());
            dataManager.sendData(geoInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getLanguage() { return language; }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public long getTimeInMils() { return time; }

    public String getCountry() {
        return country;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}