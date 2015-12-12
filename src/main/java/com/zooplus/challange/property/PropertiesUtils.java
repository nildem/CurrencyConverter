package com.zooplus.challange.property;

import java.io.IOException;

/**
 * Factory class
 */
public class PropertiesUtils {
    private static IPropertyUtil instance = null;

    private PropertiesUtils() {
    }

    public static void init(IPropertyUtil instance1) throws IOException {
        PropertiesUtils.instance = instance1;
        PropertiesUtils.instance.init();
    }

    public static IPropertyUtil getInstance() {
        if (instance == null) {
            synchronized (PropertiesUtils.class) {
                if (instance == null)
                    instance = new PropertyFileUtils(null);
            }
        }
        return instance;
    }

    public static void destroy() {
        if (instance == null) {
            synchronized (PropertiesUtils.class) {
                // Double check
                if (instance != null)
                    instance = null;
            }
        }
    }
}
