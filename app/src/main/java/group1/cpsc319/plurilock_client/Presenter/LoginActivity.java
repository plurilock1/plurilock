package group1.cpsc319.plurilock_client.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make view from res/layout/activity_login.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signInButton = (Button) findViewById(R.id.signInButton);
        final Context context = this;

        signInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Accounts page
                Intent intent = new Intent(context, AccountsActivity.class);
                startActivity(intent);
            }
        });
    }
}
