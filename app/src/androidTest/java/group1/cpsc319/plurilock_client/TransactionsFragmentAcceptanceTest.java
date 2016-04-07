package group1.cpsc319.plurilock_client;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import group1.cpsc319.plurilock_client.Presenter.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class TransactionsFragmentAcceptanceTest extends BaseAcceptanceTest {

    public TransactionsFragmentAcceptanceTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule(MainActivity.class, true);

    @Test
    public void checkRequirementsOnTransactions() {
        onData(anything()).inAdapterView(withId(R.id.listViewBankAccounts)).atPosition(0).perform(click());
        this.touchTest(R.id.textViewTransactions);
        this.swipeTest(R.id.textViewTransactions);
    }
}
