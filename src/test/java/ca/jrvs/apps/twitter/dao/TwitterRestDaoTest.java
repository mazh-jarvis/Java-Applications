package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.DaoHelper;
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
        assertTrue(DaoHelper.validateIDStr("59175"));
    }
    @Test
    public void idTest2(){
        assertFalse(DaoHelper.validateIDStr("30d95"));
    }

    /**
     * Tweeting Tests
     */
    @Test
    public void getTweetTest() {
        try {
            Tweet tweet = dao.findById("1146435093491277824");
            // System.out.println(tweet);
            assertNotNull(tweet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void saveTweetTest() {
        Tweet tweet = new Tweet("Save tweet test 2");
        Tweet result = null;
        try {
            result = dao.save(tweet);
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
            savedTweet = dao.save(new Tweet("Tweet to be deleted."));
            String idStr = savedTweet.getId_str();
            result = dao.deleteById(idStr);
            System.out.println(result); // TODO: remove debug
            assertNotNull(result);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
