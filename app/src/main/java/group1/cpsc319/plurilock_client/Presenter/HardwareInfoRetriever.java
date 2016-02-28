package group1.cpsc319.plurilock_client.Presenter;

import android.os.Build;
import android.util.Log;

/**
 * Helper class for collecting hardware specific data
 *
 * TODO: Investigate how/when to retrieve
 *  - Screen Size
 *  - Number of CPU cores
 *
 * Created by Junoh on 2/27/2016.
 */
public class HardwareInfoRetriever implements DeviceContextData {

    // Hardware Specific Information
    private String manufacturer;
    private String model;
    private int sdkVersion;

    public HardwareInfoRetriever() {
        this.model = Build.MODEL;
        this.manufacturer = Build.MANUFACTURER;
        this.sdkVersion = Build.VERSION.SDK_INT;
    }

    /**
     * Log Device Information (for testing and validation purposes)
     * Example output for a Nexus 6 device.
     *      Product: shamu
     *      Brand: google
     *      Model: Nexus 6
     *      Manufacturer: motorola
     *      SDK_Version: 23
     */
    public void logDeviceInfo() {
        Log.d(Build.PRODUCT, "Product");
        Log.d(Build.BRAND, "Brand");
        Log.d(this.getModel(), "Model");
        Log.d(this.getManufacturer(), "Manufacturer");
        Log.d(Integer.toString(this.getSdkVersion()), "SDK_Version");
    }

    public String getModel() { return model; }

    public String getManufacturer() { return manufacturer; }

    public int getSdkVersion() {
        return sdkVersion;
    }
}