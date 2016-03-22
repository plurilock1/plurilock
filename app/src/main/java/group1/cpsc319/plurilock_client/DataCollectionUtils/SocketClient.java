package group1.cpsc319.plurilock_client.DataCollectionUtils;

import android.util.Log;

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
        mWebSocketClient.send(s);
    }

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://echo.websocket.org");
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
                for (Object o : listeners) {
                    //o.notify(s);
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
