package group1.cpsc319.plurilock_client.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import group1.cpsc319.plurilock_client.R;
import group1.cpsc319.plurilock_client.DataCollectionUtils.CollectGeoInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.CollectHardwareInfo;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends Activity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make view from res/layout/activity_login.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signInButton = (Button) findViewById(R.id.signInButton);
        final Context context = this;

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Accounts page
                Intent intent = new Intent(context, AccountsActivity.class);
                startActivity(intent);
            }
        });

        // Grab Device Specific Info at Startup
        CollectGeoInfo geoInfo = new CollectGeoInfo();
        CollectHardwareInfo hardwareInfo = new CollectHardwareInfo(this);

        geoInfo.logDeviceInfo();
        hardwareInfo.logDeviceInfo();

        // Create an object of our Custom Gesture Detector Class
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        // Create a GestureDetector
        mGestureDetector = new GestureDetector(this, customGestureDetector);
        // Attach listeners that'll be called for double-tap and related gestures
        mGestureDetector.setOnDoubleTapListener(customGestureDetector);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    class CustomGestureDetector implements GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onSingleTapConfirmed");
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onDoubleTap");
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onDoubleTapEvent");
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(StartActivity.class.getSimpleName(), "onScroll");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(StartActivity.class.getSimpleName(), "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(StartActivity.class.getSimpleName(), "onFling");
            return true;
        }
    }
}
