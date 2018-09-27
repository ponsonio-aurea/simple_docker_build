package com.devfactory.devspaces.poc.dcs.client.api;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class DcsApi {
//    private static final String REST_URI = "https://cloud.docker.com/api/audit/v1/action/";

    private static final String REST_URI = "https://cloud.docker.com/api/";
    private Client client;

    public DcsApi(String user, String apiKey) {
        client = ClientBuilder.newClient().register(HttpAuthenticationFeature.basic(user, apiKey));
    }


    public JsonObject executeGet(String path) {
        Response response = client.target(REST_URI + path).request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(JsonObject.class);
    }

}
