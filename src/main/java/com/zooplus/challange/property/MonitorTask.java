package com.zooplus.challange.property;

import java.util.TimerTask;

public abstract class MonitorTask extends TimerTask {

    protected ChangeListener listener;

    protected String objectName;

    protected long lastModified;

    public MonitorTask(ChangeListener listener, String objectName) {
        this.listener = listener;
        this.objectName = objectName;
        this.lastModified = 0;
    }

    @Override
    public abstract void run();

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}