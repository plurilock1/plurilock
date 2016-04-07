package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;

/**
 * Created by BK on 16-02-29.
 */

public class GestureCompatActivity extends AppCompatActivity {
    private GestureListener gestureListener = new GestureListener(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        gestureListener.onTouchEvent(e);
        return super.onTouchEvent(e);
    }
}
