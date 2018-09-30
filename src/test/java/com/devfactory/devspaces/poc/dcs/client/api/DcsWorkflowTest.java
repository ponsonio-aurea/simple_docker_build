package com.devfactory.devspaces.poc.dcs.client.api;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class DcsWorkflowTest {
    private static final String USER = "ponsonio";
    private static final String API_KEY = "0c4776a9-9a60-4c30-8366-c06cd662d802";
    private static final String NAME = "test-alpine";
    private static final String IMAGE = USER + "/" + NAME;

    private static final String REPO_PAYLOAD = "{\"namespace\":\"" + USER + "\",\"registry\":\"registry-1.docker.io\",\"image\":\"" + IMAGE + "\",\"build_in_farm\":true,\"name\":\"" + NAME + "\",\"description\":\"\",\"privacy\":\"public\",\"owner\":\"ponsonio-aurea\",\"repository\":\"simple_docker_build\",\"provider\":\"github\",\"build_settings\":[{\"nocache\":true,\"build_context\":\"/docker-test-alpine/\",\"source_type\":\"Branch\",\"tag\":\"latest\",\"dockerfile\":\"Dockerfile\",\"source_name\":\"master\",\"autobuild\":true}],\"is_private\":false}";
    private static final String BUILD_PAYLOAD = "{\"namespace\":\"" + USER + "\",\"registry\":\"registry-1.docker.io\",\"image\":\"" + IMAGE + "\",\"build_in_farm\":true,\"name\":\"" + NAME + "\",\"description\":\"\",\"privacy\":\"public\",\"owner\":\"ponsonio-aurea\",\"repository\":\"simple_docker_build\",\"provider\":\"github\",\"build_settings\":[{\"nocache\":true,\"build_context\":\"/docker-test-alpine/\",\"source_type\":\"Branch\",\"tag\":\"latest\",\"dockerfile\":\"Dockerfile\",\"source_name\":\"master\",\"autobuild\":true}],\"is_private\":false}";


    @Test
    public void testWorkflow() throws UnsupportedEncodingException {
        DcsApi api = new DcsApi(USER, API_KEY);

        JsonObject repoResponse = api.executePost("/v2/repositories/", REPO_PAYLOAD);

        JsonObject sourceJSON = api.executePost("/api/build/v1/source/", BUILD_PAYLOAD);


        //Get resource uri
        String buildUuid = sourceJSON.getString("uuid");
        String resourceUri = sourceJSON.getString("resource_uri");
        System.out.println("buildUuid" + buildUuid);
        System.out.println("resourceUri" + buildUuid);


        JsonObject sourceEnt = api.executeGet("/api/build/v1/setting/?buildsource=" + URLEncoder.encode(resourceUri, "UTF-8"));

        sourceJSON =  sourceEnt.getJsonArray("objects").getJsonObject(0);

        Response buildResponse = api.executePostWithResponse(sourceJSON.getString("resource_uri") + "build/", sourceJSON.toString());
        String actionURL = buildResponse.getHeaderString("X-DockerCloud-Action-URI");
        JsonObject buildResponseJSON = buildResponse.readEntity(JsonObject.class);


        JsonObject buildRunJSON;
        String state;
        do {
            buildRunJSON = api.executeGet(sourceJSON.getString("resource_uri"));
            state = buildRunJSON.getString("state");
        } while (state.equals("Building") || state.equals("Empty"));

        assertEquals("Success",buildRunJSON.getString("state"));

        System.out.println("Build Run :" + buildRunJSON.toString());

        DcsLogClient logClient = new DcsLogClient(USER, API_KEY);

        String log = logClient.getLog(actionURL);

        System.out.println(log);

        api.deleteRepo(USER, NAME);
    }
}