package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TestUtil;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import static junit.framework.TestCase.*;

public class TwitterServiceTest {

    private static TwitterServiceImp service;

    @BeforeClass
    public static void setup() {
        service = new TwitterServiceImp();
    }

    @Test
    public void createTweet() {
        try {
            service.postTweet("new service tweet [coordinates x3] :)", 37.78211206,-122.40061283);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showTweet() {
        String[] fields = {"id", "createdAt", "text", "coordinates"};
        try {
            service.showTweet(TestUtil.TWEET_ID, fields);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTweet() {
        String[] ids = { "1147216480427302913" };
        try {
            service.deleteTweets(ids);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
