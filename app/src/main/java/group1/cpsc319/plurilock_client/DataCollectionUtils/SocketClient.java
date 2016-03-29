package group1.cpsc319.plurilock_client.DataCollectionUtils;

import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SocketClient {

    private static SocketClient instance = null;

    protected SocketClient() {
        connectWebSocket();
    }

    public static SocketClient getInstance() {
        if (instance == null) {
            instance = new SocketClient();
        }
        return instance;
    }


    private WebSocketClient mWebSocketClient;
    private List<Object> listeners = new ArrayList<Object>();

    public void registerListener(Object o) {
        listeners.add(o);
    }

    public void sendMessage(String s) {
        if (mWebSocketClient.getReadyState() == WebSocket.READYSTATE.OPEN) {
            mWebSocketClient.send(s);
            Log.i("Websocket", "Sending message: " + s);
        } else {
            Log.i("Websocket", "Not connected yet. Inserting into cache."); // TODO: insert into cache
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
                    // call function to lock out user
                    Log.i("Websocket", "Locked.");
                    for (Object o : listeners) {
                        //o.notify(s);
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
