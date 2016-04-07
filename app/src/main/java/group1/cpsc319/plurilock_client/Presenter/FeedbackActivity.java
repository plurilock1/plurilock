package group1.cpsc319.plurilock_client.Presenter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Keylogger.Keylogger;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-03-13.
 */
public class FeedbackActivity extends GestureCompatActivity {
    private Keylogger keylogger = Keylogger.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create a view from res/layout/activity_feedback.xml.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        // To make sure onCreate is called only once
        // (Ann is suspecting that onCreate may be called more than once due to an Android bug,
        // because Ann has seen fragments overlapping randomly):
        if (savedInstanceState == null) {
            logKeys();
        }
    }

    private void logKeys() {
        EditText editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        EditText editTextFeedback = (EditText) findViewById(R.id.editTextFeedback);

        this.bindListeners(new EditText[]{editTextSubject, editTextFeedback});
    }

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
