package group1.cpsc319.plurilock_client;


import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import group1.cpsc319.plurilock_client.Presenter.MapsActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MapActivityAcceptanceTest extends BaseAcceptanceTest {

    public MapActivityAcceptanceTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MapsActivity> aboutRule = new ActivityTestRule(MapsActivity.class, true);

    @Test
    public void checkRequirementsOnAbout() {
        this.touchTest(R.id.frameLayoutMapTouch);
        this.swipeTest(R.id.frameLayoutMapTouch);
    }
}