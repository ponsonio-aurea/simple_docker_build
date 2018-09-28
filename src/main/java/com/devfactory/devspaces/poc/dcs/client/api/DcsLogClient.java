package com.devfactory.devspaces.poc.dcs.client.api;

import com.devfactory.devspaces.poc.dcs.client.api.ws.LogSocket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;


public class DcsLogClient {
    private static final String LOG_URL_FMT = "wss://ws.cloud.docker.com/api/audit/v1/action/%s/logs";
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


    public String getLog(String uuid) {
        WebSocketClient client = new WebSocketClient();
        LogSocket socket = new LogSocket();
        try {
            client.start();

            ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setHeader("Authorization", auth);

            URI logUri = new URI(String.format(LOG_URL_FMT,"0f54102e-e3bd-4d01-834d-143c0d79d9c2"));
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

//    public static void main(String[] args) {
////        /api/audit/v1/action/(uuid)/logs/
//
////        DcsApi api = new DcsApi("user", "api-key");
////        JsonObject json = api.executeGet("audit/v1/action/");
////        assertEquals(json.getJsonObject("meta").getInt("limit"), 25);
//
//
//
//        WebSocketClient client = new WebSocketClient();
//        LogSocket socket = new LogSocket();
//        try
//        {
//            client.start();
//            URI logUri = new URI(REST_URI+"audit/v1/action/0f54102e-e3bd-4d01-834d-143c0d79d9c2/logs");
//
//
//            String authString = "ponsonio:0c4776a9-9a60-4c30-8366-c06cd662d802";
//            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes(StandardCharsets.ISO_8859_1));
//
//            String authHeader = "Basic " + new String(authEncBytes);
//
//
//            ClientUpgradeRequest request = new ClientUpgradeRequest();
//            request.setHeader("Authorization", authHeader);
//
//            Session s = client.connect(socket,logUri,request).get(10000, TimeUnit.MILLISECONDS);
//
//
//
//            System.out.printf("Connecting to : %s%n",logUri);
//
//            // wait for closed socket connection.
//            socket.awaitClose(20000, TimeUnit.MILLISECONDS);
//            System.out.println(socket.getLog());
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                client.stop();
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
}
