package group1.cpsc319.plurilock_client.DataCollectionUtils.Touch;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.Model.DataManager;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by BK on 16-02-29.
 * Edited by Matas on 16-04-06.
 */

public class GestureListener {
    private boolean fingerAlreadyDown = false;
    private int lastX = 0;
    private int lastY = 0;
    private VelocityTracker velocityTracker = null;
    private static DataManager dataManager = DataManager.getInstance();
    private static final String TAG = "Gesture";
    private Context context;

    public GestureListener() {}

    public GestureListener(Context context) {
        this.context = context;
    }

    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (!fingerAlreadyDown) {
                    Log.i(TAG, "Down" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
                }

                fingerAlreadyDown = true;
                sendTouchData(e);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "Up" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
                fingerAlreadyDown = false;

                if (velocityTracker != null) {
                    velocityTracker.clear();
                }

                sendTouchData(e);
                break;

            case MotionEvent.ACTION_MOVE:
                if (lastX != (int) e.getX() && lastY != (int) e.getY()) {
                    velocityTracker = VelocityTracker.obtain();
                    velocityTracker.addMovement(e);
                    velocityTracker.computeCurrentVelocity(1000);

                    Log.d("VELOCITY X", Float.toString(velocityTracker.getXVelocity()));
                    Log.d("VELOCITY Y", Float.toString(velocityTracker.getYVelocity()));
                    if ((int) Math.abs(velocityTracker.getXVelocity()) > 1500 || (int) Math.abs(velocityTracker.getYVelocity()) > 1500) {
                        Log.i(TAG, "Swipe" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
                    } else {
                        Log.i(TAG, "Scroll" + coordination(e) + precision(e) + getTouchType(e) + abTime(e));
                    }

                    lastX = (int) e.getX();
                    lastY = (int) e.getY();

                    sendTouchData(e);
                }

                break;
        }
        return true;
    }

    private String coordination(MotionEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        return "(" + x + " , " + y + ")";
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
            dataManager.sendData(toJSONObject(e));
        } catch (JSONException ex) {
            ex.printStackTrace();;
        }
    }

    private String getToolTypeName(MotionEvent e) {
        switch(e.getToolType(0)) {
            case MotionEvent.TOOL_TYPE_MOUSE:
                return "Mouse";

            case MotionEvent.TOOL_TYPE_STYLUS:
                return "Stylus";

            case MotionEvent.TOOL_TYPE_FINGER:
                return "Finger";

            case MotionEvent.TOOL_TYPE_ERASER:
                return "Eraser";

            case MotionEvent.TOOL_TYPE_UNKNOWN:
                return "Unknown";
        }

        return "Unknown";
    }

    private JSONObject toJSONObject(MotionEvent e) throws JSONException {
        JSONObject motionEventJson = new JSONObject();

        if (e.getPointerCount() > 1) {
            motionEventJson.put("NumFingers", e.getPointerCount());
        } else {
            motionEventJson.put("NumFingers", 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            motionEventJson.put("eventType", e.actionToString(e.getAction()));
        }

        motionEventJson.put("x", e.getX());
        motionEventJson.put("y", e.getY());
        motionEventJson.put("xPrecision", e.getXPrecision());
        motionEventJson.put("yPrecision", e.getYPrecision());
        motionEventJson.put("abTime", e.getEventTime() - e.getDownTime());
        motionEventJson.put("touchArea", e.getSize());
        motionEventJson.put("pressure", e.getPressure());

        if (e.getOrientation() == 0) {
            motionEventJson.put("orientation", "portrait");
        } else {
            motionEventJson.put("orientation", "landscape");
        }

        motionEventJson.put("toolType", this.getToolTypeName(e));

        if (context.getResources().getString(R.string.app_name) != null) {
            motionEventJson.put("application", context.getResources().getString(R.string.app_name));
        } else {
            motionEventJson.put("application", "unknown");
        }

        return motionEventJson;
    }
}
