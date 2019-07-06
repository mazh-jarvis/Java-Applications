package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Stream;

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
        Tweet tweet = getDao().findById(id);
        if(tweet == null) {
            System.err.println("ERROR: no tweet with such id");
            return;
        }
        if (fields == null)
            return;
        Stream<String> fieldStream = Arrays.asList(fields).stream();
        fieldStream.forEach(field -> {
            try {
                Field refField = tweet.getClass().getDeclaredField(field);
                PropertyDescriptor descriptor = new PropertyDescriptor(refField.getName(), tweet.getClass());
                System.out.println(descriptor.getDisplayName() + ": " + descriptor.getReadMethod().invoke(tweet, null));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
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
