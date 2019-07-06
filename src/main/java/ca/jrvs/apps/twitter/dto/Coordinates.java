package ca.jrvs.apps.twitter.dto;

public class Coordinates {
    private Double latitude, longitude;
    private String type;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // null coordinates for no coordinates
    public Coordinates() {}

    public double getLatitude() {
        return latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitudeStr() {
        return this.longitude == null? null : Double.toString(this.longitude);
    }

    public String getLatitudeStr() {
        return this.latitude == null? null : Double.toString(this.latitude);
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

