package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Singleton class for enabling GPS tracking
 *
 * To use, (1) generate an instance of the tracker @see getGPSTracker
 *         (2) set it to listen for updates at the appropriate intervals (time and distance)
 *         (3) stop tracker when it is no longer required @see stopGPSTracker
 *
 * Note: The class is currently used for just retrieving geolocation at application startup. At this
 * point, however, the cache does not contain updated geolocation information. Thus, it would return
 * a default value of {0, 0}. Instead of hanging the app until geolocation is retrieved, a dummy
 * value is injected. This still leaves the flexibility for geolocation data collection at set
 * intervals.
 *
 * Created by Junoh on 03/26/2016.
 */
public class GPSTracker extends Service implements LocationListener {

    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Singleton instance
    private static GPSTracker instance = null;

    // Flag for GPS status
    private boolean isGPSEnabled = false;

    // Flag for Network status
    private boolean isNetworkEnabled = false;

    // Flag for location services
    private boolean locationServiceAvailable = false;

    private LocationManager locationManager;

    private double longitude;

    private double latitude;

    private Location location;

    /**
     * Singleton getter
     */
    public static GPSTracker getGPSTracker(Activity activity) {
        if (instance == null) {
            instance = new GPSTracker(activity);
        }
        return instance;
    }

    /**
     * Local constructor
     */
    private GPSTracker(Activity activity) {
        initLocationService(activity);
        Log.d(this.toString(), "GPSTracker created");
    }

    /**
     * Sets up location service after permissions is granted
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initLocationService(Activity activity) {
        Log.d(this.toString(), "Initializing GPSTracker coordinates");
        // Check permissions from the user
        if (Build.VERSION.SDK_INT >= 19 &&
                ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(this.toString(), "No user permission granted");
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        try   {
            // Initialize dummy lat lon values to UBC
            // This is set for the demo environment since we will not be updating our lat/lon values there.
            // Flexibility is still left to allow for updating lat/long values with this class.
            this.longitude = 49.261138;
            this.latitude = -123.247991;
            this.locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

            // Get GPS and network status
            this.isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            this.isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled)    {
                // cannot get location
                this.locationServiceAvailable = false;
            } else {
                this.locationServiceAvailable = true;

                if (isNetworkEnabled) {
                    Log.d(this.toString(), "Network is Enabled");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null)   {
                        this.location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        updateCoordinates();
                    }
                }//end if

                if (isGPSEnabled)  {
                    Log.d(this.toString(), "GPS is enabled");
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null)  {
                        this.location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        updateCoordinates();
                    }
                }
            }
        } catch (Exception ex)  {
            Log.d("Error with GPSTracker: ", ex.getMessage());
        }
    }

    // Callback functions for location updates.
    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopGPSTracker(Activity activity){
        // Check permissions from the user
        if (Build.VERSION.SDK_INT >= 19 &&
                ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
            Log.d(this.toString(), "GPSTracker service terminated");
        }
    }

    // Helper method for updating lat lon coordinates
    private void updateCoordinates() {
        if(location != null){
            this.latitude = location.getLatitude();
            this.longitude = location.getLongitude();
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}