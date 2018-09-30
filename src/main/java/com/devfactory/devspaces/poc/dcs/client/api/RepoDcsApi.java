package com.devfactory.devspaces.poc.dcs.client.api;

import com.devfactory.devspaces.poc.dcs.client.api.dto.CreateRepoDTO;
import com.devfactory.devspaces.poc.dcs.client.api.dto.CreateSourceDTO;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class RepoDcsApi {
    private DcsApi api;

    public RepoDcsApi(String user, String apiKey) {
        api = new DcsApi(user, apiKey);
    }

    public JsonObject createRepo(CreateRepoDTO dto) {
        return api.executePost("/v2/repositories/", Entity.entity(dto, MediaType.APPLICATION_JSON));
    }

    public JsonObject createBuildConfig(CreateSourceDTO dto) throws UnsupportedEncodingException {
        JsonObject sourceJSON = api.executePost("/api/build/v1/source/", Entity.entity(dto, MediaType.APPLICATION_JSON));

        String resourceUri = sourceJSON.getString("resource_uri");
        JsonObject sourceEnt = api.executeGet("/api/build/v1/setting/?buildsource=" + URLEncoder.encode(resourceUri, "UTF-8"));

        return sourceEnt.getJsonArray("objects").getJsonObject(0);
    }

    public String triggerBuild(JsonObject sourceJSON) {
        Response buildResponse = api.executePostWithResponse(sourceJSON.getString("resource_uri") + "build/", sourceJSON.toString());
        return buildResponse.getHeaderString("X-DockerCloud-Action-URI");
    }

    public String getBuidState(String resourceUri) {
        return api.executeGet(resourceUri).getString("state");
    }

//    public JsonObject createRepoAndBuildConfig(CreateRepoDTO dto) {
//        return api.executePost("/v2/repositories/", Entity.entity(dto, MediaType.APPLICATION_JSON));
//    }
//
//    public JsonObject executeGet(String path) {
//        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON).get(JsonObject.class);
//    }
//
//    public JsonObject executePost(String path, String payload) {
//        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON)
//                .post(Entity.json(payload), JsonObject.class);
//    }
//
//    public JsonObject executePost(String path, Entity payload) {
//        Response resp =  client.target(REST_URI + path).request(MediaType.APPLICATION_JSON).post(payload);
//
//
//        return resp.readEntity(JsonObject.class);
//    }
//
//    public Response executePostWithResponse(String path, String payload) {
//        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON)
//                .post(Entity.json(payload));
//    }
//
//    public void deleteRepo(String user, String name) {
//        client.target(String.format(DELETE_URI_FMT, user, name)).request(MediaType.APPLICATION_JSON).delete();
//    }
}
