package ca.jrvs.apps.twitter.dto;

import java.util.Date;
import java.util.Map;

/*
* Description: Create a tweet with a geotag and
output the created tweet object(simplifeid version)
in JSON format.
Arguments:
tweet_text - tweet_text cannot exceed 140
UTF-8 encoded characters.
latitude:longitude - Geo location.
 */
public class Tweet {
    private Date createdAt;
    private long id;
    private String idStr;
    private String text;
    private Map<String, Entities> entities;
    private Coordinates coordinates;
    private int retweetCount, favoriteCount;
    private boolean favorited, retweeted;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Map<String, Entities> getEntities() {
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

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
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
}
