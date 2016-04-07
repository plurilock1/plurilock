package group1.cpsc319.plurilock_client;


import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import group1.cpsc319.plurilock_client.Presenter.AboutActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class AboutActivityAcceptanceTest extends BaseAcceptanceTest {

    public AboutActivityAcceptanceTest() {
        super();
    }

    @Rule
    public ActivityTestRule<AboutActivity> aboutRule = new ActivityTestRule(AboutActivity.class, true);

    @Test
    public void checkRequirementsOnAbout() {
        this.touchTest(R.id.imageViewLogoAbout);
        this.swipeTest(R.id.imageViewLogoAbout);
    }
}