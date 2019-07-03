package ca.jrvs.apps.twitter.dto;

import java.util.List;

public class Entities {
    private List<Hashtag> hashtags;
    private List<UserMention> user_mentions;

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<UserMention> getUser_mentions() {
        return user_mentions;
    }
}
