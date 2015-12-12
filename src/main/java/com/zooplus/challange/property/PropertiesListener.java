package com.zooplus.challange.property;


public class PropertiesListener implements ChangeListener {
    public void changed() {
        try {
            System.out.println("Properties changed");
            PropertiesUtils.getInstance().loadProperties();
        } catch (Exception e) {
            System.out.println("PropertiesUtils.getInstance(key).load problem");
            e.printStackTrace();
        }
    }
}
