package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-03-13.
 */
public class FeedbackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }
}
