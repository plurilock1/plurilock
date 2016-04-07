package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import group1.cpsc319.plurilock_client.DataCollectionUtils.SocketClient;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class MainActivity extends GestureCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        // To make sure onCreate is called only once
        // (Ann is suspecting that onCreate may be called more than once due to an Android bug,
        // because Ann has seen fragments overlapping randomly):
        if (savedInstanceState == null) {
            createCustomToolbar();
            initializeAccountFragment();
            SocketClient.getInstance(this);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }


    private void createCustomToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // The toolbar can be edited in res/layout/activity_accounts.xml.
    }

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
                return true;

            case R.id.action_feedback:
                // User chose the "Feedback" item, show the app settings UI...
                toFeedbackFragment();
                return true;

            case R.id.action_abm_locator:
                // User chose the "ABM Locator" item, show the app about UI...
                Intent intentABMLocator = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intentABMLocator);
                return true;

            case R.id.action_about:
                // User chose the "About" item, show the app about UI...
                Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
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

    private void toFeedbackFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        FeedbackFragment fragment = new FeedbackFragment();
        transaction.replace(R.id.frameLayoutFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
