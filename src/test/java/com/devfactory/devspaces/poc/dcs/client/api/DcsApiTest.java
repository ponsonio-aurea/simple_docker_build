package com.devfactory.devspaces.poc.dcs.client.api;

import static org.junit.Assert.*;

import javax.json.JsonObject;
import org.junit.Test;

public class DcsApiTest {

    @Test
    public void testGetAction() {
        DcsApi api = new DcsApi("ponsonio", "0c4776a9-9a60-4c30-8366-c06cd662d802");
        JsonObject json = api.executeGet("/api/audit/v1/action/");
        assertEquals(json.getJsonObject("meta").getInt("limit"), 25);
    }
}