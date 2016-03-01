package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import group1.cpsc319.plurilock_client.R;


/**
 * Created by anneunjungkim on 2016-02-27.
 */

public class AccountsActivity extends GestureCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        // Create custom toolbar
        // Toolbar can be edited in res/layout/activity_accounts.xml
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // Create overflow menu
    // Menu items can be edited in res/menu/menu.xml
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Set action for selecting menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                // User chose the "Sign out" item, show the app settings UI...
                return true;


            case R.id.action_about:
                // User chose the "About" item, show the app about UI...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
