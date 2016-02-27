package group1.cpsc319.plurilock_client.Presenter;

import android.support.v7.app.AppCompatActivity;
import group1.cpsc319.plurilock_client.Model.User;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import group1.cpsc319.plurilock_client.R;

public class StartActivity extends AppCompatActivity {
    private User test;
    private TextView tv;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        test = new User("Matas", "matas@alumni.ubc.ca");
        tv = (TextView) findViewById(R.id.text_view_1);
        tv.setText(test.userMessage());

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               tv = (TextView) findViewById(R.id.text_view_1);
               tv.setText("You clicked the button!");
           }
        });


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
