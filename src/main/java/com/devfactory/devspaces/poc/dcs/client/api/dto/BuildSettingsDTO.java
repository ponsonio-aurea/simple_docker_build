package com.devfactory.devspaces.poc.dcs.client.api.dto;

public class BuildSettingsDTO {
    private String source_type = "Branch";
    private String tag = "latest";
    private String dockerfile = "Dockerfile";
    private String source_name = "master";
    private String build_context = "/docker-test-alpine/";
    private boolean autobuild = true;
    private boolean nocache = false;

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDockerfile() {
        return dockerfile;
    }

    public void setDockerfile(String dockerfile) {
        this.dockerfile = dockerfile;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getBuild_context() {
        return build_context;
    }

    public void setBuild_context(String build_context) {
        this.build_context = build_context;
    }

    public boolean isAutobuild() {
        return autobuild;
    }

    public void setAutobuild(boolean autobuild) {
        this.autobuild = autobuild;
    }

    public boolean isNocache() {
        return nocache;
    }

    public void setNocache(boolean nocache) {
        this.nocache = nocache;
    }
}
