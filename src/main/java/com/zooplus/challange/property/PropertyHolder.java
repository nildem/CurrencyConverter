package com.zooplus.challange.property;

import java.io.Serializable;
import java.util.Date;

public class PropertyHolder implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String key;
    private String stringValue;
    private Integer intValue;
    private Long longValue;
    private Double doubleValue;
    private Boolean booleanValue;
    private Integer propertyId;
    private String engineId;
    private Date startDate;
    private Date endDate;

    /**
     * it is for database property map
     * 
     * @param key
     * @param value
     * @param propertyId
     * @param engineId
     * @param startDate
     * @param endDate
     */
    public PropertyHolder(String key, String value, Integer propertyId, String engineId, Date startDate, Date endDate) {
        this(key, value);
        this.engineId = engineId;
        this.propertyId = propertyId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * it is for file property map
     * 
     * @param key
     * @param stringValue
     */
    public PropertyHolder(String key, String stringValue) {
        this.key = key;
        setValue(stringValue);
    }

    public void setValue(String stringValue) {
        this.stringValue = (stringValue == null) ? "" : stringValue;
        try {
            this.intValue = Integer.parseInt(this.stringValue);
        } catch (Exception e) {
            // 
        }
        try {
            this.longValue = Long.parseLong(this.stringValue);
        } catch (Exception e) {
            // 
        }
        try {
            this.doubleValue = Double.parseDouble(this.stringValue);
        } catch (Exception e) {
            // 
        }
        try {
            this.booleanValue = Boolean.parseBoolean(this.stringValue);
        } catch (Exception e) {
            // 
        }
    }

    public final Date getStartDate() {
        return startDate;
    }

    public final void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public final Date getEndDate() {
        return endDate;
    }

    public final void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public final boolean hasDateLimits() {
        return startDate != null || endDate != null;
    }

    public final String getKey() {
        return key;
    }

    public final String getStringValue() {
        return stringValue;
    }

    public final Integer getIntValue() {
        return intValue;
    }

    public final Long getLongValue() {
        return longValue;
    }

    public final Double getDoubleValue() {
        return doubleValue;
    }

    public final Boolean getBooleanValue() {
        return booleanValue;
    }

    public boolean checkDateRange() {
        if (hasDateLimits()) {
            Date start = getStartDate();
            Date end = getEndDate();
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

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getEngineId() {
        return engineId;
    }

    public void setEngineId(String engineId) {
        this.engineId = engineId;
    }
}
