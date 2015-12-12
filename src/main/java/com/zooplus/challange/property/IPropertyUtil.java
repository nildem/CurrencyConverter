package com.zooplus.challange.property;

import java.io.IOException;

public interface IPropertyUtil {
    public void init() throws IOException;

    public void loadProperties() throws IOException;

    public String getProperty(String key, String defaultValue);

    public Integer getPropertyAsInteger(String key, Integer defaultValue);
}
