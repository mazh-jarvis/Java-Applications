package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.TestUtil;
import ca.jrvs.apps.twitter.TwitterUtil;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.TestCase.*;

public class TwitterRestDaoTest {

    private static TwitterRestDao dao;

    @BeforeClass
    public static void setup() {
        dao = new TwitterRestDao();
    }

    /**
     * ValidateID Challenge Tests
     */
    @Test
    public void idTest1(){
        assertTrue(TwitterUtil.validateIDStr("59175"));
    }
    @Test
    public void idTest2(){
        assertFalse(TwitterUtil.validateIDStr("30d95"));
    }

    /**
     * Tweeting Tests
     */
    @Test
    public void getTweetTest() {
        try {
            Tweet tweet = dao.findById(TestUtil.TWEET_ID);
            System.out.println(tweet);
            assertNotNull(tweet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createTweetTest() {
        Tweet tweet = new Tweet("Create tweet test 2");
        Tweet result = null;
        try {
            result = dao.create(tweet);
            assertNotNull(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteTweetTest() {
        Tweet savedTweet, result = null;
        try {
            savedTweet = dao.create(new Tweet("Tweet to be deleted."));
            String idStr = savedTweet.getIdStr();
            result = dao.deleteById(idStr);
            assertNotNull(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
