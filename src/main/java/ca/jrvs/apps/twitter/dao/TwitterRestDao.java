package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.stream.Stream;

public class TwitterRestDao implements CrdRepository<Tweet, String>, HttpHelper {
    public static final String BASE_URI = "https://api.twitter.com/1.1/statuses";

    @Override
    public HttpResponse httpPost(URI uri) {
        return null;
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
        return null;
    }

    @Override
    public HttpResponse httpGet(URI uri) throws IOException {
        HttpGet request = new HttpGet(uri.getPath());
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public Tweet save(Tweet entity) {
        return null;
    }

    @Override
    public Tweet findById(String s) {
        return null;
    }

    @Override
    public Tweet deleteById(String s) {
        return null;
    }

    public void showTweet(String screenName) throws URISyntaxException, IOException {
        URI uri = new URI(BASE_URI + "/user_timeline.json?screen_name=" + screenName);
        HttpResponse response = httpGet(uri);
        int status = response.getStatusLine().getStatusCode();
        if(status != 200) {
            System.err.println("Server responded with error " + status);
            return;
        }
        // TODO: left off
    }

    public void postTweet() {

    }

    public void deleteTweet() {

    }

    private String getShowUri() {
        return null;
    }

    // Validate ID string challenge
    public boolean validateIDStr(String id) {
        Stream<Character> charStream = id.chars().mapToObj(c -> (char)c);
        return charStream.allMatch(Character::isDigit);
    }
}
