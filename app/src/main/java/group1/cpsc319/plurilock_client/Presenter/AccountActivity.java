package group1.cpsc319.plurilock_client.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import group1.cpsc319.plurilock_client.Model.Account;
import group1.cpsc319.plurilock_client.R;

/**
 * Created by anneunjungkim on 2016-02-27.
 */
public class AccountActivity extends GestureCompatActivity {
    private List<Account> myAccounts = new ArrayList<>();

    // Create a message handling object as an anonymous class.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent intent = new Intent(AccountActivity.this, TransactionActivity.class);

            switch (position) {
                case 0:
                    startActivity(intent);
                    break;

                case 1:
                    startActivity(intent);
                    break;

                default:
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        createCustomToolbar();
        populateAccountList();
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

            case R.id.action_sign_out:
                // User chose the "Sign out" item, show the app settings UI...
                return true;

            case R.id.action_about:
                // User chose the "About" item, show the app about UI...
                Intent intent = new Intent(AccountActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
    }
}

    private void populateAccountList() {
        myAccounts.add(new Account("Chequing", "0000 0000", "$1000.00"));
        myAccounts.add(new Account("Savings", "0000 0001", "$5000.00"));
    }

    private void populateListView() {
        ArrayAdapter<Account> adapter = new MyArrayAdapter();

        ListView listViewBankAccounts = (ListView) findViewById(R.id.listViewBankAccounts);
        listViewBankAccounts.setAdapter(adapter);

        listViewBankAccounts.setOnItemClickListener(mMessageClickedHandler);
    }

    private class MyArrayAdapter extends ArrayAdapter<Account> {
        public MyArrayAdapter() {
            super(AccountActivity.this, R.layout.list_item_account, myAccounts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            // If we didn't already create an item view, create one.
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.list_item_account, parent, false);
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
}
