package group1.cpsc319.plurilock_client;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import group1.cpsc319.plurilock_client.Presenter.LoginActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginActivityAcceptanceTest extends BaseAcceptanceTest {

    public LoginActivityAcceptanceTest() {
        super();
    }

    @Rule
    public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule(LoginActivity.class, true);

    @Test
    public void checkRequirementsOnLogin() {
        this.hardwareTrackedTest();
        this.locationTrackedTest();
        this.touchTest(R.id.imageViewLogoLogin);
        this.swipeTest(R.id.imageViewLogoLogin);
        this.keyTest(R.id.editTextUsername);
    }
}