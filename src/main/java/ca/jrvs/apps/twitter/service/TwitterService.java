package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dto.Tweet;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service("service")
public interface TwitterService {
    /**
     * Post a new tweet
     * @param text tweet status content
     * @param latitude latitude(location)
     * @param longitude longitude(location)
     * @throws IOException
     * @throws URISyntaxException
     */
    Tweet postTweet(String text, Double latitude, Double longitude) throws IOException, URISyntaxException;

    /**
     * Show specific data from a tweet (by reflection)
     * @param id tweet id
     * @param fields tweet data of interest
     * @throws IOException
     * @throws URISyntaxException
     */
    Tweet showTweet(String id, String[] fields) throws IOException, URISyntaxException;

    /**
     * Delete tweets
     * @param ids tweet ids
     * @throws IOException
     * @throws URISyntaxException
     */
    List<Tweet> deleteTweets(String[] ids) throws IOException, URISyntaxException;
}
