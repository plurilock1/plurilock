package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *  Helper class for collecting device geographic data
 *
 * Created by Junoh on 2/27/2016.
 */
public class CollectGeoInfo implements CollectDeviceContextData {

    private String language;
    private TimeZone timeZone;
    private long time;
    private String country;
    private double latitude;
    private double longitude;
    private JSONObject deviceInfo;

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
        this.deviceInfo = new JSONObject();
    }

    public JSONObject collectDeviceInfo() {
        try {
            deviceInfo.put("Language", this.getLanguage());
            deviceInfo.put("Timezone", this.getTimeZone().getID());
            deviceInfo.put("Time", this.getTimeInMils());
            deviceInfo.put("CountryCode", this.getCountry());
            deviceInfo.put("Latitude", this.getLatitude());
            deviceInfo.put("Longitude", this.getLongitude());

            Log.d("Geo Info", deviceInfo.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceInfo;
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