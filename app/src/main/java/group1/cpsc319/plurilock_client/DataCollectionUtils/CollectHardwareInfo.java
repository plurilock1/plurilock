package group1.cpsc319.plurilock_client.DataCollectionUtils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;

/**
 * Helper class for collecting hardware specific data
 *
 * TODO: Investigate how/when to retrieve
 *  - Number of CPU cores
 *
 * Created by Junoh on 2/27/2016.
 */
public class CollectHardwareInfo implements CollectDeviceContextData {

    private String manufacturer;
    private String model;
    private int sdkVersion;
    private int screenWidth;
    private int screenHeight;

    public CollectHardwareInfo(Activity activity) {
        this.model = Build.MODEL;
        this.manufacturer = Build.MANUFACTURER;
        this.sdkVersion = Build.VERSION.SDK_INT;

        //Calculate screen size
        Display display = activity.getWindowManager().getDefaultDisplay();
        // display size in pixels
        Point size = new Point();

        display.getSize(size);
        this.screenWidth = size.x;
        this.screenHeight = size.y;
    }

    /**
     * Log Device Information (for testing and validation purposes)
     * Example output for a Nexus 6 device.
     *      Product: shamu
     *      Brand: google
     *      Model: Nexus 6
     *      Manufacturer: motorola
     *      SDK_Version: 23
     *      Screen_Width: 1080
     *      Screen_Height: 1776
     */
    public void logDeviceInfo() {
        Log.d(Build.PRODUCT, "Product");
        Log.d(Build.BRAND, "Brand");
        Log.d(this.getModel(), "Model");
        Log.d(this.getManufacturer(), "Manufacturer");
        Log.d(Integer.toString(this.getSdkVersion()), "SDK_Version");
        Log.d(Integer.toString(this.getScreenWidth()), "Screen_Width");
        Log.d(Integer.toString(this.getScreenHeight()), "Screen_Height");
    }

    public String getModel() { return model; }

    public String getManufacturer() { return manufacturer; }

    public int getSdkVersion() { return sdkVersion; }

    public int getScreenWidth() { return screenWidth; }

    public int getScreenHeight() { return screenHeight; }
}