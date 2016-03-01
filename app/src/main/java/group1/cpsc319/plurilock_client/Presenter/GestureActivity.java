package group1.cpsc319.plurilock_client.Presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import group1.cpsc319.plurilock_client.TouchGesture.GestureListener;

/**
 * Created by BK on 16-02-29.
 */

public class GestureActivity extends Activity {
    private GestureDetector gestureDetector;

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
}
