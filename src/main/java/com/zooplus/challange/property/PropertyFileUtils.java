package com.zooplus.challange.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertyFileUtils implements IPropertyUtil {
    private File propertiesFile = null;
    private Map<String, PropertyHolder> propertiesMap;
    private PropertiesListener listener;
    public static final SimpleDateFormat DATE_LIMIT_FORMAT = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    public static final String SUFFIX_STARTDATE = ".startDate";
    public static final String SUFFIX_ENDDATE = ".endDate";

    public PropertyFileUtils(File propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public synchronized void init() throws IOException {
        if (propertiesFile == null) {
            throw new RuntimeException("Error occured. Property file name is empty.");
        }
        if (!propertiesFile.exists()) {
            throw new RuntimeException("Error occured. Property file is not exist.");
        }
        this.propertiesMap = new HashMap<String, PropertyHolder>();
        loadProperties();
        listener = new PropertiesListener();
        WatchdogMonitor.getInstance().addFileChangeListener(listener, propertiesFile.getPath(), 1000 * 60);
    }

    public void loadProperties() throws IOException {
        if (propertiesFile == null) {
            throw new RuntimeException("Property file is not set. Call init() before load()");
        }
        if (propertiesMap != null) {
            propertiesMap.clear();
        }
        FileInputStream fis = new FileInputStream(propertiesFile);
        Properties properties = new Properties();
        properties.load(fis);
        fis.close();
        Map<String, String> kv = new HashMap<String, String>();
        for (Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            String value = ((String) entry.getValue()).trim();
            propertiesMap.put(key, new PropertyHolder(key, value));
            kv.put(key, value);
        }
        // update start-endDates for props
        for (Map.Entry<String, String> e : kv.entrySet()) {
            String key = e.getKey();
            // if a date limit property, remove it
            if (updateLoadedProperties(key, e.getValue())) {
                propertiesMap.remove(key);
            }
        }
        kv.clear();
        kv = null;
    }

    private boolean updateLoadedProperties(String key, String value) {
        String k = null;
        if (key.endsWith(SUFFIX_STARTDATE)) {
            k = key.substring(0, key.indexOf(SUFFIX_STARTDATE));
            PropertyHolder p = propertiesMap.get(k);
            if (p != null) {
                p.setStartDate(getDateLimit(value));
                return true;
            }
            System.err.println("startDate param for unknown property: " + key);
        }
        if (key.endsWith(SUFFIX_ENDDATE)) {
            k = key.substring(0, key.indexOf(SUFFIX_ENDDATE));
            PropertyHolder p = propertiesMap.get(k);
            if (p != null) {
                p.setEndDate(getDateLimit(value));
                return true;
            }
            System.err.println("endDate param for unknown property: " + key);
        }
        return false;
    }

    private Date getDateLimit(String value) {
        synchronized (DATE_LIMIT_FORMAT) {
            try {
                return DATE_LIMIT_FORMAT.parse(value);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * If there is no value, return default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public synchronized Integer getPropertyAsInteger(String key, Integer defaultValue) {
        PropertyHolder p = getPropertyInstance(key);
        if (p == null || p.getIntValue() == null) {
            return defaultValue;
        }
        return p.getIntValue();
    }

    /**
     * If there is no value, return default value.
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public synchronized String getProperty(String key, String defaultValue) {
        PropertyHolder p = getPropertyInstance(key);
        if (p == null || p.getStringValue() == null) {
            return defaultValue;
        }
        return p.getStringValue();
    }

    public PropertyHolder getPropertyInstance(String key) {
        PropertyHolder p = propertiesMap.get(key);
        if (p == null || !dateControl(p)) {
            return null;
        }
        return p;
    }

    private boolean dateControl(PropertyHolder p) {
        if (p.hasDateLimits()) {
            Date start = p.getStartDate();
            Date end = p.getEndDate();
            Date now = DateTimeUtils.getInstance().getUTCNowDateTime();
            if (start != null && end != null) {
                return now.after(start) && now.before(end);
            } else if (start != null) {
                return now.after(start);
            } else if (end != null) {
                return now.before(end);
            }
        }
        return true;
    }
}
