package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Keylogger.Keylogger;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-03-13.
 */
public class FeedbackFragment extends Fragment {
    private Keylogger keylogger = Keylogger.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout and add keylogger for this fragment.
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        EditText editTextSubject = (EditText) view.findViewById(R.id.editTextSubject);
        EditText editTextFeedback = (EditText) view.findViewById(R.id.editTextFeedback);

        this.bindListeners(new EditText[]{editTextSubject, editTextFeedback});

        return view;
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
