package com.devfactory.devspaces.poc.dcs.client.api;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class DcsApi {
    private static final String REST_URI = "https://cloud.docker.com";
    private static final String DELETE_URI_FMT = REST_URI + "/v2/repositories/%s/%s/";
    private Client client;

    public DcsApi(String user, String apiKey) {
        client = ClientBuilder.newClient().register(HttpAuthenticationFeature.basic(user, apiKey));
    }

    public JsonObject executeGet(String path) {
        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON).get(JsonObject.class);
    }

    public JsonObject executePost(String path, String payload) {
        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON)
                .post(Entity.json(payload), JsonObject.class);
    }

    public JsonObject executePost(String path, Entity payload) {
        Response resp =  client.target(REST_URI + path).request(MediaType.APPLICATION_JSON).post(payload);


        return resp.readEntity(JsonObject.class);
    }

    public Response executePostWithResponse(String path, String payload) {
        return client.target(REST_URI + path).request(MediaType.APPLICATION_JSON)
                .post(Entity.json(payload));
    }

    public void deleteRepo(String user, String name) {
        client.target(String.format(DELETE_URI_FMT, user, name)).request(MediaType.APPLICATION_JSON).delete();
    }
}
