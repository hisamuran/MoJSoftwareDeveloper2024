package com.challenge.enums;

public enum TransportTypes {
    BUS("Bus"),
    TUBE("Tube");

    private String transportType;

    private TransportTypes(String transportType) {
        this.transportType = transportType;
    }

    public String getTransportType() {
        return this.transportType;
    }
}
