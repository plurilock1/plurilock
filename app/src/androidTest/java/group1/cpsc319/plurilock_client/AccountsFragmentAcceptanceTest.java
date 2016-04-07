package group1.cpsc319.plurilock_client;


import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import group1.cpsc319.plurilock_client.Presenter.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class AccountsFragmentAcceptanceTest extends BaseAcceptanceTest {

    public AccountsFragmentAcceptanceTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule(MainActivity.class, true);

    @Test
    public void checkRequirementsOnAccounts() {
        this.touchTest(R.id.frameFragment);
        this.swipeTest(R.id.frameFragment);
    }
}
