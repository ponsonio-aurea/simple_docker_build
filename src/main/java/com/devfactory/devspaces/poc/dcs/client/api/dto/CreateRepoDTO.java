package com.devfactory.devspaces.poc.dcs.client.api.dto;

import java.util.Arrays;
import java.util.List;

public class CreateRepoDTO {
    private String namespace;
    private String registry;
    private boolean build_in_farm;
    private String name;
    private String description;
    private String privacy;
    private boolean is_private;
    private List<BuildSettingsDTO> build_settings;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public String getImage() {
        return namespace + "/" + name;
    }

    public boolean isBuild_in_farm() {
        return build_in_farm;
    }

    public void setBuild_in_farm(boolean build_in_farm) {
        this.build_in_farm = build_in_farm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public List<BuildSettingsDTO> getBuild_settings() {
        return build_settings;
    }

    public void setBuild_settings(
            List<BuildSettingsDTO> build_settings) {
        this.build_settings = build_settings;
    }
}

