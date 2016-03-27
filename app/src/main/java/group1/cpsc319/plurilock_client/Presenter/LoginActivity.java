package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import group1.cpsc319.plurilock_client.R;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectGeoInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Context.CollectHardwareInfo;
import group1.cpsc319.plurilock_client.DataCollectionUtils.Keylogger.Keylogger;


/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class LoginActivity extends GestureActivity {
    private Keylogger keylogger = Keylogger.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create a view from res/layout/activity_login.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startAccountActivity();
    }

    private void startAccountActivity() {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

        this.bindListeners(new EditText[]{username, password});

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

    //TODO: if used enough in our app, this needs to be abstracted
    private void bindListeners(EditText[] editTextElements) {
        if (!(editTextElements instanceof EditText[])) {
            throw new IllegalArgumentException("class must be of type EditText[]");
        }

        for (EditText element: editTextElements) {
            element.setOnEditorActionListener(keylogger);
            element.addTextChangedListener(keylogger);
        }
    }
}

