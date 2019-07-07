package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Entities {
    @JsonProperty("hashtags")
    private List<Hashtag> hashtags;
    @JsonProperty("user_mentions")
    private List<UserMention> userMentions;

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<UserMention> getUserMentions() {
        return userMentions;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "hashtags=" + hashtags +
                ", userMentions=" + userMentions +
                '}';
    }
}
