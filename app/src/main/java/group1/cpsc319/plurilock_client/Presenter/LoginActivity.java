package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import group1.cpsc319.plurilock_client.R;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectGeoInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectHardwareInfo;


/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends GestureActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create a view from res/layout/activity_login.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // To make sure onCreate is called only once
        // (Ann is suspecting that onCreate may be called more than once due to an Android bug,
        // because Ann has seen fragments overlapping randomly):
        if (savedInstanceState == null) {
            startAccountActivity();
        }

    }

    private void startAccountActivity() {
        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Grab Device Specific Info at Startup
        CollectGeoInfo geoInfo = new CollectGeoInfo();
        CollectHardwareInfo hardwareInfo = new CollectHardwareInfo(this);

        geoInfo.collectDeviceInfo();
        hardwareInfo.collectDeviceInfo();
    }
}
