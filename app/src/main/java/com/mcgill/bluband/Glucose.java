package com.mcgill.bluband;

public class Glucose {
    public String glucoseLevel, timestamp;

    public Glucose() {
    }

    public Glucose(String glucoseLevel, String timestamp) {
        this.glucoseLevel = glucoseLevel;
        this.timestamp = timestamp;
    }

    public String getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(String glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
