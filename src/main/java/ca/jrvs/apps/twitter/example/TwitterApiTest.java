package ca.jrvs.apps.twitter.example;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class TwitterApiTest {
    private static final String CONSUMER_KEY = "MYzrKCMu6U1f06ia1rGRUtzGk",
        CONSUMER_SECRET = "QMkiLlMnb15ecSfcoubmevfgIFGxR2KYPv52KBvRbVvczjAPOq",
        ACCESS_TOKEN = "1144304278728843264-vIU3F7ybqpK8EbcPoBfSHrVyRMVixC",
        TOKEN_SECRET = "4my4bJyQrd2FLkqJuMW3urdlpD79GMMnwMqBtwNSmT7ZT";
    private static final String TW_SEARCH_RQ =
            "https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump";

    public static void main(String[] args) {

        OAuthConsumer consumer =
                new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        // create http get request
        HttpGet request = new HttpGet(TW_SEARCH_RQ);

        // sign the request
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        System.out.println("Http Request Headers:");

        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        // send/exec the request
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
