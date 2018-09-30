package com.devfactory.devspaces.poc.dcs.client.api.dto;

import java.util.List;

public class CreateSourceDTO {
    private String autotests;
    private boolean build_in_farm;
    private String owner;
    private String repository;
    private String channel;
    private String image;
    private String provider;
    private List<BuildSettingsDTO> build_settings;
    private List<EnvVarDTO> envvars;

    public String getAutotests() {
        return autotests;
    }

    public void setAutotests(String autotests) {
        this.autotests = autotests;
    }

    public boolean isBuild_in_farm() {
        return build_in_farm;
    }

    public void setBuild_in_farm(boolean build_in_farm) {
        this.build_in_farm = build_in_farm;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<BuildSettingsDTO> getBuild_settings() {
        return build_settings;
    }

    public void setBuild_settings(
            List<BuildSettingsDTO> build_settings) {
        this.build_settings = build_settings;
    }

    public List<EnvVarDTO> getEnvvars() {
        return envvars;
    }

    public void setEnvvars(List<EnvVarDTO> envvars) {
        this.envvars = envvars;
    }
}

