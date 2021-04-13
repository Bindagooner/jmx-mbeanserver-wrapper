package com.poc.mbean;

public interface GenericMXBean {

    String getName();
    void setName(String name);
    String getAttribute(String attributeName);
    void setAttribute(String name, String value);
}
