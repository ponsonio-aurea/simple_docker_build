package com.devfactory.devspaces.poc.dcs.client.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DcsLogClientTest {

    @Test
    public void testGetLog() {
        DcsLogClient client = new DcsLogClient("user", "api-key");
        String log = client.getLog("0f54102e-e3bd-4d01-834d-143c0d79d9c2");
        System.out.println(log);
        assertTrue(log.contains("Build finished"));
    }
}