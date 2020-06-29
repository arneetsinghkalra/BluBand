package com.mcgill.bluband;

public class Glucose {
    public String BGLevel, timestamp;

    public Glucose() {
    }

    public Glucose(String BGLevel, String timestamp) {
        this.BGLevel = BGLevel;
        this.timestamp = timestamp;
    }

    public String getBGLevel() {
        return BGLevel;
    }

    public void setBGLevel(String BGLevel) { this.BGLevel = BGLevel; }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
