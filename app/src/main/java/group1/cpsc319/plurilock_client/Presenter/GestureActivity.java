package group1.cpsc319.plurilock_client.Presenter;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;

/**
 * Created by BK on 16-02-29.
 */

public class GestureActivity extends Activity {
    private GestureDetector gestureDetector;

    public static final String TAG = "Orientation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create an object of our Custom Gesture Detector Class
        GestureListener customGestureDetector = new GestureListener();

        // Create a GestureDetector
        gestureDetector = new GestureDetector(this, customGestureDetector);
        gestureDetector.setOnDoubleTapListener(customGestureDetector);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "Layout changes to landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(TAG, "Layout changes to portrait");
        }
    }
}
