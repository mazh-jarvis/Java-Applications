package ca.jrvs.apps.twitter.dao.helper;

public class Keys {
    public static String
            CONSUMER_KEY = System.getenv("CONSUMER_KEY"),
            CONSUMER_SECRET = System.getenv("CONSUMER_SECRET"),
            ACCESS_TOKEN = System.getenv("ACCESS_TOKEN"),
            TOKEN_SECRET = System.getenv("TOKEN_SECRET");
}
