package com.devfactory.devspaces.poc.dcs.client.api.ws;

import java.io.StringReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class LogSocket {
    private final CountDownLatch closeLatch;
    private String log = "";


    public LogSocket() {
        this.closeLatch = new CountDownLatch(1);
    }

    public String getLog() {
        return log;
    }

    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
        return this.closeLatch.await(duration,unit);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        this.closeLatch.countDown();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) { }

    @OnWebSocketMessage
    public void onMessage(String msg)
    {
        JsonObject obj = getJsonObject(msg);
        if (obj.getString("type").equals("log")) {
            log += obj.getString("log");
        }
    }

    private static JsonObject getJsonObject(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        return (JsonObject) reader.read();
    }
}