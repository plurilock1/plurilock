package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectGeoInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectHardwareInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Keylogger.Keylogger;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.GPSTracker;
import group1.cpsc319.plurilock_client.R;


/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends GestureActivity {
    private Keylogger keylogger = Keylogger.getInstance();

    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create a view from res/layout/activity_login.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // To make sure onCreate is called only once
        // (Ann is suspecting that onCreate may be called more than once due to an Android bug,
        // because Ann has seen fragments overlapping randomly):
        if (savedInstanceState == null) {
            startAccountActivity();
        }
    }

    private void startAccountActivity() {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        this.bindListeners(new EditText[]{username, password});

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

    //TODO: if used enough in our app, this needs to be abstracted
    private void bindListeners(EditText[] editTextElements) {
        if (!(editTextElements instanceof EditText[])) {
            throw new IllegalArgumentException("class must be of type EditText[]");
        }

        for (EditText element: editTextElements) {
            element.setOnEditorActionListener(keylogger);
            element.addTextChangedListener(keylogger);
        }
    }
}

