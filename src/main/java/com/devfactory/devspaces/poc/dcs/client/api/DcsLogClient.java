package com.devfactory.devspaces.poc.dcs.client.api;

import com.devfactory.devspaces.poc.dcs.client.api.ws.LogSocket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;


public class DcsLogClient {
    private static final String LOG_URL_FMT = "wss://ws.cloud.docker.com%slogs";
    private int timeout = 5;
    private String auth;

    public DcsLogClient(String user, String apiKey) {
        String authString = user + ":" + apiKey;
        byte[] authEncBytes = Base64.encodeBase64(authString.getBytes(StandardCharsets.ISO_8859_1));
        auth = "Basic " + new String(authEncBytes);
    }

    public DcsLogClient(String user, String apiKey, int timeout) {
        this(user, apiKey);
        this.timeout = timeout;
    }


    public String getLog(String actionURL) {
        WebSocketClient client = new WebSocketClient();
        LogSocket socket = new LogSocket();
        try {
            client.start();

            ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setHeader("Authorization", auth);

            URI logUri = new URI(String.format(LOG_URL_FMT, actionURL));
            client.connect(socket,logUri,request);
            socket.awaitClose(timeout, TimeUnit.SECONDS);
            return socket.getLog();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
