package com.poc.mbean;

import java.util.HashMap;
import java.util.Map;

public class Sample implements GenericMXBean {

    private Map<String, String> attributes = new HashMap<>();
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    @Override
    public void setAttribute(String name, String value) {
        attributes.put(name, value);
    }
}
