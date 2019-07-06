package ca.jrvs.apps.twitter.service;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
            service.showTweet("1147220067316359168", fields);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
