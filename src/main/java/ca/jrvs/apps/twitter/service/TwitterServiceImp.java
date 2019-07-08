package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TwitterUtil;
import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class TwitterServiceImp implements TwitterService {

//    private static TwitterRestDao dao;
    private CrdRepository dao;

    public TwitterServiceImp(CrdRepository dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) throws IOException, URISyntaxException {
        Tweet tweet = new Tweet(text);
        tweet.setCoordinates(new Coordinates(latitude, longitude));
        Tweet result = (Tweet) dao.create(tweet);
        if (result == null)
            throw new NullPointerException();
        else if (result.getText().isEmpty())
            throw new InvalidObjectException(TwitterUtil.INVALID_EX_MSG);
        return result;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) throws IOException, URISyntaxException {

        Tweet tweet = (Tweet) dao.findById(id);
        if(tweet == null)
            throw new NoSuchElementException();
        if (fields == null)
            throw new InvalidParameterException();
        Stream<String> fieldStream = Arrays.asList(fields).stream();
        fieldStream.forEach(field -> {
            try {
                Field refField = tweet.getClass().getDeclaredField(field);
                PropertyDescriptor descriptor = new PropertyDescriptor(refField.getName(), tweet.getClass());
                long resultId =  (long) descriptor
                        .getReadMethod()
                        .invoke(tweet);
                System.out.println( descriptor.getDisplayName() + ": " + resultId );
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        });
        return tweet;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) throws IOException, URISyntaxException {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            Tweet tweet = (Tweet) dao.deleteById(id);
            if (tweet != null && tweet.getText().length() == 0)
                throw new InvalidObjectException(TwitterUtil.INVALID_EX_MSG);
            tweets.add(tweet);
        }
        return tweets;
    }
/*
    private static TwitterRestDao dao {
        if(dao == null)
            dao = new TwitterRestDao();
        return dao;
    }*/
}
