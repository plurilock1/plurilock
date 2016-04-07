package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.view.MenuItem;

import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */

public class AboutActivity extends GestureCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

