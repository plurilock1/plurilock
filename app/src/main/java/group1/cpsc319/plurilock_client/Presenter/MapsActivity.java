package group1.cpsc319.plurilock_client.Presenter;


import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.GPSTracker;

import group1.cpsc319.plurilock_client.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GestureListener gestureListener = new GestureListener(this);
    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addTouchLayer();
    }

    private void addTouchLayer() {

        FrameLayout frameLayoutMapTouch = (FrameLayout) findViewById(R.id.frameLayoutMapTouch);
        frameLayoutMapTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureListener.onTouchEvent(event);
                return onTouchEvent(event);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        gpsTracker = GPSTracker.getInstance(this);

        LatLng loc;
        double lat = gpsTracker.getLatitude();
        double lon = gpsTracker.getLongitude();

        // If GPSTracker is functional and lat/lon values are not the default values at 0
        if (gpsTracker != null && lat != 0 && lon != 0) {
            loc = new LatLng(lat, lon);
        } else {
            // Default is set to UBC
            loc = new LatLng(49.261169, -123.248119);
        }
        // Add location marker and move the camera with a zoom of 15
        mMap.addMarker(new MarkerOptions().position(loc).title("Marker at current location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
    }
}
