package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.TwitterUtil;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class TwitterServiceApp {

    public static void main(String[] args) {
        Scanner parser;
        if(args.length < 1) {
            System.err.println(TwitterUtil.SERVICE_USAGE_TEMP);
            return;
        }
        TwitterServiceImp service = new TwitterServiceImp(new TwitterRestDao());
        String cmd = args[0];

        String id = null;
        Tweet tweet = null;
        switch (cmd) {
            case "post":
                String text = args[1];
                parser = new Scanner(args[2]);
                Double longitude = parser.nextDouble();
                Double latitude = parser.nextDouble();
                try {
                    tweet = service.postTweet(text, latitude, longitude);
                    if(tweet != null)
                        System.out.printf(TwitterUtil.POST_SUCCESS_MSG_FORMAT, tweet.getIdStr());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
            case "show":
                id = args[1];
                if(args.length < 3) {
                    System.err.println(TwitterUtil.SERVICE_APP_USAGE);
                    return;
                }
                int fields_len = args.length - TwitterUtil.CMD_ARG_INDEX;
                String[] fields = new String[fields_len];
                System.arraycopy(args, TwitterUtil.CMD_ARG_INDEX, fields, 0, fields_len);
                try {
                    service.showTweet(id, fields);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                List<Tweet> tweets = null;
                String[] ids = new String[args.length - 1];
                System.arraycopy(args, 2, ids, 0, args.length);
                try {
                     tweets = service.deleteTweets(ids);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                if(tweets == null) {
                    System.err.println("Delete: " + TwitterUtil.INVALID_EX_MSG);
                }
                System.out.println("Successfully deleted: ");
                tweets.forEach(t -> System.out.println(t.getIdStr()));
                break;
        }
    }
}
