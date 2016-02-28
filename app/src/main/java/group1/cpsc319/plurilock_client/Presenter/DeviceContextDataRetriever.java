package group1.cpsc319.plurilock_client.Presenter;

import android.os.Build;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Helper class that defines methods pertaining to the collection of context sensitive data
 * including:
 *  -       Language
 *  -       Hardware Model
 *  -       SDK version
 *  -       Time Zone
 *  -       Date/Time
 *  -       Country Code
 *
 *  TODO: Investigate how/when to retrieve
 *  - Screen Size
 *  - Number of CPU cores
 *  - Location: lat/lon
 *
 * Created by Junoh on 2/20/2016.
 */
public class DeviceContextDataRetriever {

    private String language;
    private String manufacturer;
    private String model;
    private int sdkVersion;
    private TimeZone timeZone;
    private long time;
    private String country;

    public DeviceContextDataRetriever() {
        this.language = Locale.getDefault().getISO3Language();
        this.model = Build.MODEL;
        this.manufacturer = Build.MANUFACTURER;
        this.sdkVersion = Build.VERSION.SDK_INT;

        // Retrieve current time zone, date and time
        Calendar cal = Calendar.getInstance();
        this.timeZone = cal.getTimeZone();
        this.time = cal.getTimeInMillis();
        this.country = Locale.getDefault().getISO3Country();
    }

    /**
     * Log Device Information (for testing and validation purposes)
     * Example Information is for a Nexus 6 device.
     *      Product: shamu
     *      Brand: google
     *      Model: Nexus 6
     *      Manufacturer: motorola
     *      Language: eng
     *      SDK_Version: 23
     *      Time_Zone: America/Vancouver
     *      Time: 14556628177951
     *      Country Code: USA
     */
    public void logDeviceInfo() {
        Log.d(Build.PRODUCT, "Product");
        Log.d(Build.BRAND, "Brand");
        Log.d(this.getModel(), "Model");
        Log.d(this.getManufacturer(), "Manufacturer");
        Log.d(this.getLanguage(), "Language");
        Log.d(Integer.toString(this.getSdkVersion()), "SDK_Version");
        Log.d(this.getTimeZone().getID(), "Time_Zone");
        Log.d(Long.toString(this.getTimeInMils()), "Time");
        Log.d(this.getCountry(), "Country Code");
    }

    public String getLanguage() { return language; }

    public String getModel() { return model; }

    public String getManufacturer() { return manufacturer; }

    public int getSdkVersion() {
        return sdkVersion;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public long getTimeInMils() { return time; }

    public String getCountry() {
        return country;
    }
}