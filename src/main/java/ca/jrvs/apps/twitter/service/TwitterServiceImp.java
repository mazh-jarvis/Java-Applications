package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TwitterUtil;
import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Service
public class TwitterServiceImp implements TwitterService {

    private CrdRepository dao;

    @Autowired
    public TwitterServiceImp(CrdRepository dao) {
        this.dao = dao;
    }

    @Override
    public Tweet postTweet(String text, Double latitude, Double longitude) throws IOException, URISyntaxException {
        TwitterUtil.validatePostTweet(text, longitude, latitude);
        Tweet tweet = new Tweet(text);
        tweet.setCoordinates(new Coordinates(latitude, longitude));
        Tweet result = (Tweet) dao.create(tweet);
        if (result == null)
            throw new NullPointerException();
        else if (result.getText().isEmpty())
            throw new InvalidObjectException(TwitterUtil.INVALID_RESPONSE_EX);
        return result;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) throws IOException, URISyntaxException {
        Tweet tweet = (Tweet) dao.findById(id);
        if(tweet == null)
            throw new NoSuchElementException();
        if (fields == null)
            throw new IllegalArgumentException("Invalid fields argument");
        Stream<String> fieldStream = Arrays.asList(fields).stream();
        fieldStream.forEach(field -> {
            try {
                Field refField = tweet.getClass().getDeclaredField(field);
                PropertyDescriptor descriptor = new PropertyDescriptor(refField.getName(), tweet.getClass());
                Object fieldValue = descriptor
                        .getReadMethod()
                        .invoke(tweet);
                System.out.println( descriptor.getDisplayName() + ": " + fieldValue );
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
    public List<Tweet> deleteTweets(String[] ids) throws IOException, URISyntaxException, NoSuchElementException {
        List<Tweet> tweets = new ArrayList<>();
        for (String id : ids) {
            Tweet tweet = (Tweet) dao.deleteById(id);
            if (tweet == null)
                throw new NoSuchElementException(TwitterUtil.NO_SUCH_TWEET_EX);
            else if (tweet.getText().length() == 0)
                throw new InvalidObjectException(TwitterUtil.INVALID_RESPONSE_EX);
            tweets.add(tweet);
        }
        return tweets;
    }
}
