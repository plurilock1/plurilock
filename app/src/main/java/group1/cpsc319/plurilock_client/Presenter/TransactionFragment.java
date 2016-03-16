package group1.cpsc319.plurilock_client.Presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import group1.cpsc319.plurilock_client.Model.Transaction;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-03-13.
 */
public class TransactionFragment extends Fragment {
    private List<Transaction> myTransactions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateTransactionList();
    }

    private void populateTransactionList() {
        myTransactions.add(new Transaction("16/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("15/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("14/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("13/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("12/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("11/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("10/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("9/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("8/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("7/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("6/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("5/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("4/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("3/Feb/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("2/Feb/2016", "Siegel's Bagels", "($5.00)"));
        myTransactions.add(new Transaction("1/Feb/2016", "Interest", "$0.05"));
        myTransactions.add(new Transaction("31/Jan/2016", "Nick Sushi", "($10.00)"));
        myTransactions.add(new Transaction("30/Jan/2016", "Siegel's Bagels", "($5.00)"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        ListView listViewBankAccounts = (ListView) view.findViewById(R.id.listViewAccountTransactions);

        ArrayAdapter<Transaction> adapter = new MyArrayAdapter();
        listViewBankAccounts.setAdapter(adapter);

        return view;
    }

    private class MyArrayAdapter extends ArrayAdapter<Transaction> {
        public MyArrayAdapter() {
            super(getActivity(), R.layout.list_item_transaction, myTransactions);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            // If we didn't already create an item view, create one.
            if (itemView == null) {
                itemView = getActivity().getLayoutInflater().inflate(R.layout.list_item_transaction, parent, false);
            }

            // Select an account from the account list.
            Transaction currentTransaction = myTransactions.get(position);

            // Insert the type of the current account.
            TextView textViewType = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewType.setText(currentTransaction.getDate());

            // Insert the ID of the current account.
            TextView textViewID = (TextView) itemView.findViewById(R.id.textViewInfo);
            textViewID.setText(currentTransaction.getInfo());

            // Insert the balance of the current account.
            TextView textViewBalance = (TextView) itemView.findViewById(R.id.textViewAmount);
            textViewBalance.setText(currentTransaction.getAmount());

            return itemView;
        }
    }
}
