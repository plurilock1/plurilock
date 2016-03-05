package group1.cpsc319.plurilock_client.DataCollectionUtils.Touch;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.util.Log;
import android.view.View;

/**
 * Created by BK on 16-02-29.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    public static final String TAG = "GestureListener";

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i(TAG, "Single Tap Up" + coordinate(e) + getTouchType(e));
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i(TAG, "Long Press" + coordinate(e) + getTouchType(e));
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY){
        Log.i(TAG, "Scroll" + scrollCoordinate(e1, e2) + getTouchType(e1));
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        Log.i(TAG, "Fling" + scrollCoordinate(e1, e2) + getTouchType(e1));
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i(TAG, "Show Press" + coordinate(e) + getTouchType(e));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i(TAG, "Down" + coordinate(e) + getTouchType(e));
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i(TAG, "Double tap" + coordinate(e) + getTouchType(e));
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i(TAG, "Event within double tap" + coordinate(e) + getTouchType(e));
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i(TAG, "Single tap confirmed" + coordinate(e) + getTouchType(e));
        return false;
    }

    private static String coordinate(MotionEvent e) {
        int x = (int)e.getX();
        int y = (int)e.getY();
        String a = "(" + x + " , " + y + ")";
        return a;
    }

    private static String scrollCoordinate(MotionEvent e1, MotionEvent e2) {
        int x1 = (int)e1.getX();
        int y1 = (int)e1.getY();
        int x2 = (int)e2.getX();
        int y2 = (int)e2.getY();

        String a = "(" + (x2 - x1) + " , " + (y2 - y1) + ")";
        return a;
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

}
