package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {
    private static final int LONG_INDEX = 0;
    private static final int LAT_INDEX = 1;
    @JsonProperty("type")
    private String type;
    @JsonProperty("coordinates")
    private List<Double> coordinates;

    public Coordinates(Double latitude, Double longitude) {
        coordinates = new ArrayList<>();
        coordinates.add(longitude);
        coordinates.add(latitude);
    }

    // null coordinates for no coordinates
    public Coordinates() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitudeStr() {
        if (coordinates == null) return null;
        return coordinates.get(LONG_INDEX).toString();
    }


    public String getLatitudeStr() {
        if (coordinates == null) return null;
        return coordinates.get(LAT_INDEX).toString();
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}

