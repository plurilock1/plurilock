package group1.cpsc319.plurilock_client.Presenter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;
import group1.cpsc319.plurilock_client.Model.DataManager;

/**
 * Created by BK on 16-02-29.
 */

public class GestureCompatActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private static DataManager dataManager = DataManager.getInstance();
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

        JSONObject obj = new JSONObject();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            try {
                obj.put("Orientation", "landscape");
                dataManager.sendData(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            try {
                obj.put("Orientation", "portrait");
                dataManager.sendData(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}