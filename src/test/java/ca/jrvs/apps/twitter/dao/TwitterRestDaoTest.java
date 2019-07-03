package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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
        assertTrue(dao.validateIDStr("59175"));
    }
    @Test
    public void idTest2(){
        assertFalse(dao.validateIDStr("30d95"));
    }

    /**
     * Tweeting Tests
     */
    @Test
    public void getTweetTest() {
        try {
            Tweet tweet = dao.findById("realDonaldTrump");
//            System.out.println(">> TWEET: " + tweet);
            assertNotNull(tweet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
