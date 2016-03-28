package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectGeoInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectHardwareInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.GPSTracker;
import group1.cpsc319.plurilock_client.R;


/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends GestureActivity {

    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create a view from res/layout/activity_login.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startAccountActivity();
    }

    private void startAccountActivity() {
        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Check if there are location permissions.
        if (Build.VERSION.SDK_INT >= 19 &&
                ContextCompat.checkSelfPermission(
                        this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // If no permissions, make the request
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            // If permission exists, collect device data
            gpsTracker = GPSTracker.getInstance(this);
            collectHardwareData();
            collectGeoData(gpsTracker);
            gpsTracker.stopGPSTracker();
        }
    }

    /**
     * Callback function with response for user location permission request.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // If permissions granted, initialize GPS Tracker
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            gpsTracker = GPSTracker.getInstance(this);
        } else {
            gpsTracker = null;
        }
        // Grab Device Specific Info
        CollectGeoInfo geoInfo = new CollectGeoInfo(this, gpsTracker);
        CollectHardwareInfo hardwareInfo = new CollectHardwareInfo(this);

        geoInfo.collectDeviceInfo();
        hardwareInfo.collectDeviceInfo();

        if (gpsTracker != null) {
            gpsTracker.stopGPSTracker();
        }
    }

    private void collectHardwareData() {
        CollectHardwareInfo hardwareInfo = new CollectHardwareInfo(this);
        hardwareInfo.collectDeviceInfo();
    }

    private void collectGeoData(GPSTracker tracker) {
        CollectGeoInfo geoInfo = new CollectGeoInfo(this, tracker);
        geoInfo.collectDeviceInfo();
    }
}
