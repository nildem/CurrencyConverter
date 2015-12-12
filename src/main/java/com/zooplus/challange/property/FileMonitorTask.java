package com.zooplus.challange.property;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class FileMonitorTask extends MonitorTask {

    private File monitoredFile;

    public FileMonitorTask(ChangeListener listener, String fileName) throws FileNotFoundException {
        super(listener, fileName);
        monitoredFile = new File(fileName);
        if (!monitoredFile.exists()) { // but is it on CLASSPATH?
            URL fileURL = listener.getClass().getClassLoader().getResource(fileName);
            if (fileURL != null) {
                monitoredFile = new File(fileURL.getFile());
            } else {
                throw new FileNotFoundException("File Not Found: " + fileName);
            }
        }
        this.lastModified = monitoredFile.lastModified();
    }

    @Override
    public void run() {
        long actualLastModified = monitoredFile.lastModified();
        if (actualLastModified != this.lastModified) {
            this.lastModified = actualLastModified;
            this.listener.changed();
        }
    }
}