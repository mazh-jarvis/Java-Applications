package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TestUtil;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.URISyntaxException;
import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.JVM)
public class TwitterServiceTest {

    private static TwitterServiceImp service;
    private static Tweet dummyTweet;

    @BeforeClass
    public static void setup() {
        service = new TwitterServiceImp();
    }

    @Test
    public void createTweet() {
        try {
            dummyTweet = service.postTweet("new service tweet [coordinates x4] :)", 37.78211206,-122.40061283);
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
        String[] ids = { dummyTweet.getIdStr() }; //{ "1147216480427302913" };
        try {
            service.deleteTweets(ids);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
