package group1.cpsc319.plurilock_client.DataCollectionUtils;

import android.app.Activity;

public class Plurilock {
    public static void init(Activity a) {
        SocketClient.getInstance(a);
    }
}
