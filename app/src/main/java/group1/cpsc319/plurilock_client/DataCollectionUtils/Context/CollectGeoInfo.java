package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import android.util.Log;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *  Helper class for collecting device geographic data
 *
 *  TODO: Investigate how/when to retrieve
 *  - Location: lat/lon
 *
 * Created by Junoh on 2/27/2016.
 */
public class CollectGeoInfo implements CollectDeviceContextData {

    private String language;
    private TimeZone timeZone;
    private long time;
    private String country;

    public CollectGeoInfo() {
        this.language = Locale.getDefault().getISO3Language();
        Calendar cal = Calendar.getInstance();
        this.timeZone = cal.getTimeZone();
        this.time = cal.getTimeInMillis();
        this.country = Locale.getDefault().getISO3Country();
    }

    public void collectDeviceInfo() {
        Log.d(this.getLanguage(), "Language");
        Log.d(this.getTimeZone().getID(), "Time_Zone");
        Log.d(Long.toString(this.getTimeInMils()), "Time");
        Log.d(this.getCountry(), "Country Code");
    }

    public String getLanguage() { return language; }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public long getTimeInMils() { return time; }

    public String getCountry() {
        return country;
    }
}