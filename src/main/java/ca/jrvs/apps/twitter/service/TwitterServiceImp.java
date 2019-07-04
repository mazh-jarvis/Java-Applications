package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.io.IOException;
import java.net.URISyntaxException;

public class TwitterServiceImp implements TwitterService {

    private static TwitterRestDao dao;

    @Override
    public void postTweet(String text, Double latitude, Double longitude) throws IOException, URISyntaxException {
        Tweet tweet = new Tweet(text);
        tweet.setCoordinates(new Coordinates(latitude, longitude));
        getDao().create(tweet);
    }

    @Override
    public void showTweet(String id, String[] fields) throws IOException, URISyntaxException {
        // TODO: implement(using reflection, or jsonToMap()) when FIELDS are given in the cli
        Tweet tweet = getDao().findById(id);
        if(tweet == null) {
            System.err.println("ERROR: no tweet with such id");
            return;
        }

    }

    @Override
    public void deleteTweets(String[] ids) throws IOException, URISyntaxException {
        for (String id : ids) {
            getDao().deleteById(id);
        }
    }

    private static TwitterRestDao getDao() {
        if(dao == null)
            dao = new TwitterRestDao();
        return dao;
    }
}
