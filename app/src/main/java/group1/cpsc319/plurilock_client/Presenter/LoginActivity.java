package group1.cpsc319.plurilock_client.Presenter;

import android.app.Activity;
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
        // Create a view from res/layout/activity_login.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startAccountActivity();
    }

    private void startAccountActivity() {
        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
