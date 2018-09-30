package com.devfactory.devspaces.poc.dcs.client.api;

import static org.junit.Assert.assertEquals;

import com.devfactory.devspaces.poc.dcs.client.api.dto.BuildSettingsDTO;
import com.devfactory.devspaces.poc.dcs.client.api.dto.CreateRepoDTO;
import com.devfactory.devspaces.poc.dcs.client.api.dto.CreateSourceDTO;
import com.devfactory.devspaces.poc.dcs.client.api.dto.EnvVarDTO;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;

public class DcsWorkflowTest1 {
    private static final String USER = "ponsonio";
    private static final String API_KEY = "0c4776a9-9a60-4c30-8366-c06cd662d802";
    private static final String NAME = "test-alpine";
    private static final String IMAGE = USER + "/" + NAME;

    private static final String REPO_PAYLOAD = "{\"namespace\":\"" + USER + "\",\"registry\":\"registry-1.docker.io\",\"image\":\"" + IMAGE + "\",\"build_in_farm\":true,\"name\":\"" + NAME + "\",\"description\":\"\",\"privacy\":\"public\",\"owner\":\"ponsonio-aurea\",\"repository\":\"simple_docker_build\",\"provider\":\"github\",\"build_settings\":[{\"nocache\":true,\"build_context\":\"/docker-test-alpine/\",\"source_type\":\"Branch\",\"tag\":\"latest\",\"dockerfile\":\"Dockerfile\",\"source_name\":\"master\",\"autobuild\":true}],\"is_private\":false}";
    private static final String BUILD_PAYLOAD = "{\"namespace\":\"" + USER + "\",\"registry\":\"registry-1.docker.io\",\"image\":\"" + IMAGE + "\",\"build_in_farm\":true,\"name\":\"" + NAME + "\",\"description\":\"\",\"privacy\":\"public\",\"owner\":\"ponsonio-aurea\",\"repository\":\"simple_docker_build\",\"provider\":\"github\",\"build_settings\":[{\"nocache\":true,\"build_context\":\"/docker-test-alpine/\",\"source_type\":\"Branch\",\"tag\":\"latest\",\"dockerfile\":\"Dockerfile\",\"source_name\":\"master\",\"autobuild\":true}],\"is_private\":false}";


    @Test
    public void testWorkflow() throws UnsupportedEncodingException, InterruptedException {
        RepoDcsApi repoApi = new RepoDcsApi(USER, API_KEY);
        DcsApi api = new DcsApi(USER, API_KEY);

        JsonObject repoResponse = repoApi.createRepo(createDTO());

        JsonObject sourceJSON = repoApi.createBuildConfig(createSourceDTO());

        String actionURL = repoApi.triggerBuild(sourceJSON);


        String state;
        do {
            Thread.currentThread().sleep(1000);
            state = repoApi.getBuidState(sourceJSON.getString("resource_uri"));
        } while (state.equals("Building") || state.equals("Empty"));

        assertEquals("Success", state);

        DcsLogClient logClient = new DcsLogClient(USER, API_KEY);

        String log = logClient.getLog(actionURL);

        api.deleteRepo(USER, NAME);
    }

    private CreateRepoDTO createDTO() {
        CreateRepoDTO dto = new CreateRepoDTO();
        dto.setNamespace("ponsonio");
        dto.setRegistry("registry-1.docker.io");
        dto.setBuild_in_farm(true);
        dto.setName("test-alpine");
        dto.setDescription("description");
        dto.setPrivacy("public");
        dto.setIs_private(false);
        dto.setBuild_settings(Arrays.asList(createBuildSettingsDTO()));

        return dto;
    }

    private BuildSettingsDTO createBuildSettingsDTO() {
        BuildSettingsDTO dto =new BuildSettingsDTO();
        dto.setSource_type("Branch");
        dto.setTag("latest");
        dto.setDockerfile("Dockerfile");
        dto.setSource_name("master");
        dto.setBuild_context("/docker-test-alpine/");
        dto.setAutobuild(true);
        dto.setNocache(false);

        return dto;
    }

    private CreateSourceDTO createSourceDTO() {
        CreateSourceDTO dto = new CreateSourceDTO();
        dto.setAutotests("OFF");
        dto.setBuild_in_farm(true);
        dto.setOwner("ponsonio-aurea");
        dto.setRepository("simple_docker_build");
        dto.setChannel("Stable");
        dto.setImage("ponsonio/test-alpine");
        dto.setProvider("github");
        dto.setBuild_settings(Arrays.asList(createBuildSettingsDTO()));
        dto.setEnvvars(Arrays.asList(createEnvVarsDTO()));

        return dto;
    }

    private EnvVarDTO createEnvVarsDTO() {
        EnvVarDTO dto = new EnvVarDTO();
        dto.setKey("Test_KEY");
        dto.setValue("Test_Value");

        return dto;
    }
}