package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Helper class for collecting hardware specific data
 *
 * Created by Junoh on 2/27/2016.
 */
public class CollectHardwareInfo implements CollectDeviceContextData {

    private String manufacturer;
    private String model;
    private int sdkVersion;
    private int screenWidth;
    private int screenHeight;
    private JSONObject deviceInfo;


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
        this.deviceInfo = new JSONObject();
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
    public JSONObject collectDeviceInfo() {
        try {
            deviceInfo.put("Product", Build.PRODUCT);
            deviceInfo.put("Brand", Build.BRAND);
            deviceInfo.put("Model", this.getModel());
            deviceInfo.put("Manufacturer", this.getManufacturer());
            deviceInfo.put("Number of Cores", this.getNumberOfCores());
            deviceInfo.put("SDKVersion", this.getSdkVersion());
            deviceInfo.put("ScreenWidth", this.getScreenWidth());
            deviceInfo.put("ScreenHeight", this.getScreenHeight());

            Log.d(Build.PRODUCT, "Product");
            Log.d(Build.BRAND, "Brand");
            Log.d(this.getModel(), "Model");
            Log.d(this.getManufacturer(), "Manufacturer");
            Log.d(Integer.toString(this.getNumberOfCores()), "Number of Cores");
            Log.d(Integer.toString(this.getSdkVersion()), "SDK_Version");
            Log.d(Integer.toString(this.getScreenWidth()), "Screen_Width");
            Log.d(Integer.toString(this.getScreenHeight()), "Screen_Height");

            Log.d("TEST", deviceInfo.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return deviceInfo;
    }

    //Heavily inspired by the information at: http://makingmoneywithandroid.com/forum/showthread.php?tid=298 (link is down at the moment)
    private int getNumberOfCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }

                return false;
            }
        }

        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());

            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public String getModel() { return model; }

    public String getManufacturer() { return manufacturer; }

    public int getSdkVersion() { return sdkVersion; }

    public int getScreenWidth() { return screenWidth; }

    public int getScreenHeight() { return screenHeight; }
}