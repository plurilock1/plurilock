package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;
import group1.cpsc319.plurilock_client.Model.Account;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-03-13.
 */
public class AccountFragment extends Fragment {
    private List<Account> myAccounts = new ArrayList<>();
    private GestureDetector gestureDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateAccountList();
    }

    private void populateAccountList() {
        myAccounts.add(new Account("Chequing", "0000 0000", "$1000.00"));
        myAccounts.add(new Account("Savings", "0000 0001", "$5000.00"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);

        ListView listViewBankAccounts = (ListView) view.findViewById(R.id.listViewBankAccounts);

        ArrayAdapter<Account> adapter = new MyArrayAdapter();
        listViewBankAccounts.setAdapter(adapter);

        listViewBankAccounts.setOnItemClickListener(mMessageClickedHandler);

        GestureListener listGestureDetector = new GestureListener();

        gestureDetector = new GestureDetector(getActivity(), listGestureDetector);
        gestureDetector.setOnDoubleTapListener(listGestureDetector);

        listViewBankAccounts.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return getActivity().onTouchEvent(event);
            }
        });

        return view;
    }

    private class MyArrayAdapter extends ArrayAdapter<Account> {
        public MyArrayAdapter() {
            super(getActivity(), R.layout.list_item_account, myAccounts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            // If we didn't already create an item view, create one.
            if (itemView == null) {
                itemView = getActivity().getLayoutInflater().inflate(R.layout.list_item_account, parent, false);
            }

            // Select an account from the account list.
            Account currentAccount = myAccounts.get(position);

            // Insert the type of the current account.
            TextView textViewType = (TextView) itemView.findViewById(R.id.textViewType);
            textViewType.setText(currentAccount.getType());

            // Insert the ID of the current account.
            TextView textViewID = (TextView) itemView.findViewById(R.id.textViewID);
            textViewID.setText(currentAccount.getID());

            // Insert the balance of the current account.
            TextView textViewBalance = (TextView) itemView.findViewById(R.id.textViewBalance);
            textViewBalance.setText(currentAccount.getBalance());

            return itemView;
        }
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            switch (position) {
                case 0:
                    toTransactionFragment();
                    break;

                case 1:
                    toTransactionFragment();
                    break;

                default:
                    toTransactionFragment();
                    break;
            }
        }
    };

    private void toTransactionFragment() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        TransactionFragment fragment = new TransactionFragment();
        transaction.replace(R.id.frameFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
