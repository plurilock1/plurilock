package group1.cpsc319.plurilock_client.Presenter;

import android.support.v7.app.AppCompatActivity;
import group1.cpsc319.plurilock_client.Model.User;
import android.os.Bundle;
import android.widget.TextView;
import group1.cpsc319.plurilock_client.R;

public class StartActivity extends AppCompatActivity {
    private User test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        test = new User("Matas", "matas@alumni.ubc.ca");
        TextView tv = (TextView) findViewById(R.id.text_view_1);
        tv.setText(test.userMessage());
    }
}
