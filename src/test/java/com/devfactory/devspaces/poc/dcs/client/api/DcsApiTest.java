package com.devfactory.devspaces.poc.dcs.client.api;

import static org.junit.Assert.*;

import javax.json.JsonObject;
import org.junit.Test;

public class DcsApiTest {

    @Test
    public void testGetAction() {
        DcsApi api = new DcsApi("user", "api-key");
        JsonObject json = api.executeGet("audit/v1/action/");
        assertEquals(json.getJsonObject("meta").getInt("limit"), 25);
    }
}