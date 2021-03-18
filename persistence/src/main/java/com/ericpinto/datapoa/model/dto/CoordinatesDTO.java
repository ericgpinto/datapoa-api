package com.ericpinto.datapoa.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordinatesDTO {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "CoordinatesDTO{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
