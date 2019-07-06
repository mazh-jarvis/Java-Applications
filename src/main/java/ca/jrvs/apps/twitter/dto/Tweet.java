package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Description: Create a tweet with a geotag and
output the created_at tweet object(simplifeid version)
in JSON format.
Arguments:
tweet_text - tweet_text cannot exceed 140
UTF-8 encoded characters.
latitude:longitude - Geo location.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
    public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";
    @JsonProperty("created_at")
    private Date created_at;
    private long id;
    private String id_str;
    private String text;
    private Entities entities;
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("long")
    private double longitude;
    private int retweet_count, favorite_count;
    private boolean favorited, retweeted;
    private Coordinates coordinates;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Default constructor is required by the json parser
    public Tweet() {}

    public Tweet(String text) {
        this.text = text;
    }

    public void setCreated_at(String created_at) throws ParseException {
        this.created_at = new SimpleDateFormat(DATE_FORMAT).parse(created_at);
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreated_at() {
        return created_at.toString();
    }

    public Entities getEntities() {
        return entities;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "created_at=" + created_at +
                ", id=" + id +
                ", id_str='" + id_str + '\'' +
                ", text='" + text + '\'' +
                ", entities=" + entities +
                ", coordinates=" + coordinates +
                ", retweet_count=" + retweet_count +
                ", favorite_count=" + favorite_count +
                ", favorited=" + favorited +
                ", retweeted=" + retweeted +
                '}';
    }
}
