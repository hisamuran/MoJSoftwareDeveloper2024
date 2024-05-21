package com.challenge.enums;

public enum StationsAndZones {
    HOLBORN("1"),
    EARLSCOURT("1,2"),
    WIMBLEDON("3"),
    HAMMERSMITH("2");

    private String zone;

    private StationsAndZones(String zone) {
        this.zone = zone;
    }

    public String getZone() {
        return this.zone;
    }

}
