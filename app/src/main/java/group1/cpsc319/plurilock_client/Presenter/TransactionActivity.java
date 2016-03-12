package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import group1.cpsc319.plurilock_client.DataCollectionUtils.Touch.GestureListener;
import group1.cpsc319.plurilock_client.Model.Transaction;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class TransactionActivity extends GestureCompatActivity {
    private List<Transaction> myTransactions = new ArrayList<>();
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        createCustomToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateTransactionList();
        populateListView();
    }

    private void createCustomToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // The toolbar can be edited in res/layout/activity_accounts.xml.
    }

    // Create an overflow menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
        // Menu items can be edited in res/menu/menu.xml.
    }

    // Set action for selecting menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Respond to the action bar's Up/Home button
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_sign_out:
                // User chose the "Sign out" item, show the app settings UI...
                return true;

            case R.id.action_about:
                // User chose the "About" item, show the app about UI...
                Intent intent = new Intent(TransactionActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
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

    private void populateListView() {
        ArrayAdapter<Transaction> adapter = new MyArrayAdapter();

        ListView listViewBankAccounts = (ListView) findViewById(R.id.listViewAccountTransactions);
        listViewBankAccounts.setAdapter(adapter);

        GestureListener listGestureDetector = new GestureListener();

        gestureDetector = new GestureDetector(this, listGestureDetector);
        gestureDetector.setOnDoubleTapListener(listGestureDetector);

        listViewBankAccounts.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return onTouchEvent(event);
            }
        });
    }

    private class MyArrayAdapter extends ArrayAdapter<Transaction> {
        public MyArrayAdapter() {
            super(TransactionActivity.this, R.layout.list_item_transaction, myTransactions);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            // If we didn't already create an item view, create one.
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_item_transaction, parent, false);
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
