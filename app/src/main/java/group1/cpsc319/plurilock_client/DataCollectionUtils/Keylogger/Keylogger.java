package group1.cpsc319.plurilock_client.DataCollectionUtils.Keylogger;

import android.text.TextWatcher;
import android.text.Editable;
import android.util.Log;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView.OnEditorActionListener;

import org.json.JSONException;
import org.json.JSONObject;

import group1.cpsc319.plurilock_client.Model.DataManager;


public class Keylogger implements TextWatcher, OnEditorActionListener {
    private static Keylogger instance;
    private static final String TAG = "Keylogger";
    private static DataManager dataManager = DataManager.getInstance();

    private Keylogger() {}

    public static Keylogger getInstance() {
        if (instance == null) {
            instance = new Keylogger();
        }

        return instance;
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        JSONObject obj = new JSONObject();
        try {
            if (count < before) {
                obj.put("keyPressed", "backspace");
            } else {
                obj.put("keyPressed", Character.toString(s.charAt(s.length()-1)));
            }

            obj.put("timestamp", System.currentTimeMillis());
            Log.d(TAG, obj.toString());
            dataManager.sendData(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        JSONObject obj = new JSONObject();
        try {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    actionId == EditorInfo.IME_ACTION_GO ||
                    actionId == EditorInfo.IME_ACTION_NEXT) {

                obj.put("keyPressed", "enter");
                obj.put("timestamp", System.currentTimeMillis());
                Log.d(TAG, obj.toString());
                dataManager.sendData(obj);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void afterTextChanged(Editable s) {
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }
}
