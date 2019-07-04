package ca.jrvs.apps.twitter.service;

import java.io.IOException;
import java.net.URISyntaxException;

public interface TwitterService {
    void postTweet(String text, Double latitude, Double longitude) throws IOException, URISyntaxException;
    void showTweet(String id, String[] fields) throws IOException, URISyntaxException;
    void deleteTweets(String[] ids) throws IOException, URISyntaxException;
}
