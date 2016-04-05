package group1.cpsc319.plurilock_client.DataCollectionUtils.Touch;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.Model.DataManager;

/**
 * Created by BK on 16-02-29.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    public static final String TAG = "GestureListener";

    private static DataManager dataManager = DataManager.getInstance();

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "Single Tap Up" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "Long Press" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY){
        Log.i(TAG, "Scroll" + scrollCoordination(e1, e2) + getTouchType(e1));
        sendTouchData(e1);
        sendTouchData(e2);
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Log.i(TAG, "Fling" + scrollCoordination(e1, e2) + getTouchType(e1));
        sendTouchData(e1);
        sendTouchData(e2);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "Show Press" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG, "Down" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "Double tap" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(TAG, "Event within double tap" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG, "Single tap confirmed" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
        sendTouchData(e);
        return false;
    }

    private String coordination(MotionEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        return "(" + x + " , " + y + ")";
    }

    private String scrollCoordination(MotionEvent e1, MotionEvent e2) {
        int x1 = (int)e1.getX();
        int y1 = (int)e1.getY();
        int x2 = (int)e2.getX();
        int y2 = (int)e2.getY();

        return "(" + (x2 - x1) + " , " + (y2 - y1) + ")";
    }

    private String precision(MotionEvent e){
        int x = (int)e.getXPrecision();
        int y = (int)e.getYPrecision();
        return "(" + x + " , " + y + ")";
    }

    //
    private String abTime(MotionEvent e) {
        long eventDuration = e.getEventTime() - e.getDownTime();
        return "(" + eventDuration+ "ms)";
    }


    private static String getTouchType(MotionEvent e) {
        String touchTypeDescription = " ";
        int touchType = e.getToolType(0);
        if (touchType == 1) {
            touchTypeDescription += "(finger)";
        } else {
            touchTypeDescription += "(unknown)";
        }
        return touchTypeDescription;
    }

    private void sendTouchData (MotionEvent e) {
        try {
            dataManager.sendData(toJSONObject(e), DataManager.TOUCH_DATA);
        } catch (JSONException ex) {
            ex.printStackTrace();;
        }
    }

    private JSONObject toJSONObject(MotionEvent e) throws JSONException {
        JSONObject motionEventJson = new JSONObject();

        motionEventJson.put("x", e.getX());
        motionEventJson.put("y", e.getY());
        motionEventJson.put("xPrecision", e.getXPrecision());
        motionEventJson.put("yPrecision", e.getYPrecision());
        motionEventJson.put("abTime", e.getEventTime() - e.getDownTime());
        motionEventJson.put("touchType", e.getToolType(0));

        return motionEventJson;
    }
}