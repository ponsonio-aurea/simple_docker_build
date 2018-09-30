package com.devfactory.devspaces.poc.dcs.client.api;

import javax.json.JsonObject;
import org.junit.Test;

public class DcsTriggerBuildTest {
    private static final String TRIGGER_PATH_FMT = "build/v1/setting/%s/build/";

    @Test
    public void testGetAction() {
        DcsApi api = new DcsApi("ponsonio", "0c4776a9-9a60-4c30-8366-c06cd662d802");

        String jsonStr = "{\"autobuild\":true,\"build_context\":\"/docker-test-alpine/\",\"buildsource\":\"/api/build/v1/source/c8134043-108a-48d5-90ff-43c470da30c7/\",\"dockerfile\":\"Dockerfile\",\"nocache\":true,\"resource_uri\":\"/api/build/v1/setting/52613661-adba-47e7-a1f7-542bc422c537/\",\"source_name\":\"master\",\"source_type\":\"Branch\",\"state\":\"Success\",\"tag\":\"latest\"}";

        JsonObject json = api.executePost("/api/build/v1/setting/52613661-adba-47e7-a1f7-542bc422c537/build/", jsonStr);
        System.out.println("####"+json);
    }
}