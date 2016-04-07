package group1.cpsc319.plurilock_client;

import android.os.SystemClock;
import android.support.test.espresso.matcher.ViewMatchers;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import group1.cpsc319.plurilock_client.Model.DataManager;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;


public abstract class BaseAcceptanceTest {
    private DataManager dm = DataManager.getInstance();
    private JSONObject obj;

    public void hardwareTrackedTest() {
        try {
            //used to bypass situation where first two json objects are stored upon activity initialization
            this.obj = dm.getDataCache().getJSONObject(0);
        } catch (JSONException e) {
            Assert.assertTrue("Could not fetch JSON object", false);
        }

        if (checkJSONFields(obj, new String[] {"Product", "Brand", "Model", "Manufacturer", "Number of Cores", "SDKVersion", "ScreenWidth", "ScreenHeight"})) {
            Assert.assertTrue("Hardware info tracked", true);
        } else {
            Assert.assertTrue(this.constructErrorMessage(obj, "Hardware"), false);
        }

        SystemClock.sleep(1000);
    }

    public void locationTrackedTest() {
        this.obj = dm.getCurrentObject();
        if (checkJSONFields(obj, new String[] {"Language", "Timezone", "Time", "CountryCode", "Latitude", "Longitude"})) {
            Assert.assertTrue("Location info tracked", true);
        } else {
            Assert.assertTrue(this.constructErrorMessage(obj, "Location"), false);
        }
        SystemClock.sleep(1000);
    }

    public void touchTest(int viewId) {
        onView(ViewMatchers.withId(viewId)).perform(click());
        this.obj = dm.getCurrentObject();
        if (checkJSONFields(obj, new String[] {"x", "y", "xPrecision", "yPrecision", "abTime", "touchType"})) {
            Assert.assertTrue("Touch info tracked", true);
        } else {
            Assert.assertTrue(this.constructErrorMessage(obj, "Touch"), false);
        }
        SystemClock.sleep(1000);
    }

    public void swipeTest(int viewId) {
        onView(ViewMatchers.withId(viewId)).perform(swipeRight());
        this.obj = dm.getCurrentObject();
        try {
            if (checkJSONFields(obj, new String[] {"x", "y", "xPrecision", "yPrecision", "abTime", "touchType"}) && (obj.getInt("xPrecision") > 0 || obj.getInt("yPrecision") > 0)) {
                Assert.assertTrue("Swipe info tracked", true);
            } else {
                Assert.assertTrue(this.constructErrorMessage(obj, "Swipe"), false);
            }
        } catch (JSONException e) {
            Assert.assertTrue("could not fetch JSON field", false);
        }

        SystemClock.sleep(1000);
    }

    public void keyTest(int viewId) {
        onView(ViewMatchers.withId(viewId)).perform(click()).perform(typeText("c"));
        SystemClock.sleep(1000);
        this.obj = dm.getCurrentObject();
        if (checkJSONFields(obj, new String[] {"keyPressed", "timestamp"})) {
            Assert.assertTrue("Key press tracked", true);
        } else {
            Assert.assertTrue(this.constructErrorMessage(obj, "Key"), false);
        }
        SystemClock.sleep(1000);
    }

    private boolean checkJSONFields(JSONObject obj, String[] fields) {
        for (int ii = 0; ii < fields.length; ii++) {
            if (!obj.has(fields[ii])) {
                return false;
            }
        }

        return true;
    }

    private String constructErrorMessage(JSONObject obj, String objType) {
        String message = (obj != null) ? obj.toString() : "obj is null";
        return objType + " info has NOT be tracked properly!: " + message;
    }
}
