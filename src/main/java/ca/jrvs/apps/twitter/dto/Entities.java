package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class Entities {
    private List<Hashtag> hashtags;
    private List<UserMention> userMentions;

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<UserMention> getUserMentions() {
        return userMentions;
    }
}
