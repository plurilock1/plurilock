package group1.cpsc319.plurilock_client.DataCollectionUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import group1.cpsc319.plurilock_client.Presenter.LoginActivity;

public class SocketClient {

    private static SocketClient instance = null;
    private static Activity activity;
    private static Context context;

    protected SocketClient() {
        connectWebSocket();
    }

    public static SocketClient getInstance() {
        if (instance == null) {
            instance = new SocketClient();
        }
        return instance;
    }

    public static SocketClient getInstance(Activity a) {
        if (instance == null) {
            instance = new SocketClient();
        }
        activity = a;
        context = a.getApplicationContext();
        return instance;
    }

    private WebSocketClient mWebSocketClient;
    private List<Object> listeners = new ArrayList<Object>();

    public void registerListener(Object o) {
        listeners.add(o);
    }

    public boolean sendMessage(String s) {
        if (mWebSocketClient.getReadyState() == WebSocket.READYSTATE.OPEN) {
            try {
                mWebSocketClient.send(s);
                Log.i("Websocket", "Sending message: " + s);
                return true;
            } catch (WebsocketNotConnectedException e) {
                Log.i("Websocket", "Sending failed.");
                return false;
            }
        } else {
            Log.i("Websocket", "Not connected yet.");
            return false;
        }
    }

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://btdemo.plurilock.com:8095");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello from Plurilock!");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                Log.i("Websocket", "Message received");
                Log.i("Websocket", s);

                if (s.split("\\$", 2)[1].equalsIgnoreCase("lock")) {
                    Random rand = new Random();
                    if (rand.nextInt(25) < 1) {

                        Log.i("Websocket", "Locked.");

                        activity.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(context.getApplicationContext(), "Deauthenticated. Please log in again.", Toast.LENGTH_LONG).show();
                            }
                        });

                        for (Object o : listeners) {
                            //o.notify(s);
                        }

                        // call function to lock out user
                        Intent i = new Intent(context, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(i);
                    }
                } else if (s.split("\\$", 2)[1].equalsIgnoreCase("ack")) {
                    Log.i("Websocket", "Acknowledged. Still authenticated.");
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
