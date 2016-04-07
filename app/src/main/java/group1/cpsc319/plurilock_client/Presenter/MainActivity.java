package group1.cpsc319.plurilock_client.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class MainActivity extends GestureCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To make sure onCreate is called only once
        // (Ann is suspecting that onCreate may be called more than once due to an Android bug,
        // because Ann has seen fragments overlapping randomly):
        if (savedInstanceState == null) {
            initializeAccountFragment();
        }
    }

//    private void createCustomToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        // The toolbar can be edited in res/layout/activity_accounts.xml.
//    }


    // Create an overflow menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
        // Menu items can be edited in res/menu/menu.xml.
    }

    // Set action for selecting menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_sign_out:
                // User chose the "Sign out" item, show the app settings UI...
                Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogout);
                finish();

                Context context = getApplicationContext();
                CharSequence text = "You have successfully signed out.";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                return true;

            case R.id.action_feedback:
                // User chose the "Feedback" item, show the app settings UI...
                Intent intentFeedback = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(intentFeedback);
                return true;

            case R.id.action_abm_locator:
                // User chose the "ABM Locator" item, show the app about UI...
                Intent intentABMLocator = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intentABMLocator);
                return true;

            case R.id.action_about:
                // User chose the "About" item, show the app about UI...
                Intent intentAbout = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intentAbout);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void initializeAccountFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        AccountFragment fragment = new AccountFragment();
        transaction.add(R.id.frameLayoutFragment, fragment);
        transaction.commit();
    }
}
