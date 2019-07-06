package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {
    public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("id")
    private long id;
    @JsonProperty("id_str")
    private String idStr;
    @JsonProperty("text")
    private String text;
    @JsonProperty("entities")
    private Entities entities;
    /*@JsonProperty("lat")
    private double latitude;
    @JsonProperty("long")
    private double longitude;*/
    @JsonProperty("retweet_count")
    private int retweetCount;
    @JsonProperty("favorite_count")
    private int favorite_count;
    @JsonProperty("favorited")
    private boolean favorited;
    @JsonProperty("retweeted")
    private boolean retweeted;
    @JsonProperty("coordinates")
    private Coordinates coordinates;

    // Default constructor is required by the json parser
    public Tweet() {}

    public Tweet(String text) {
        this.text = text;
    }

    public void setCreatedAt(String createdAt) throws ParseException {
        this.createdAt = new SimpleDateFormat(DATE_FORMAT).parse(createdAt);
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreatedAt() {
        return createdAt.toString();
    }

    public Entities getEntities() {
        return entities;
    }

    public String getCoordinates() {
        return coordinates.toString();
    }

    public Coordinates getCoordinatesObj() {
        return coordinates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
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
                "createdAt=" + createdAt +
                ", id=" + id +
                ", idStr='" + idStr + '\'' +
                ", text='" + text + '\'' +
                ", entities=" + entities +
                ", coordinates=" + coordinates +
                ", retweetCount=" + retweetCount +
                ", favorite_count=" + favorite_count +
                ", favorited=" + favorited +
                ", retweeted=" + retweeted +
                '}';
    }
}
