package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

@Component
public class TwitterCLIRunner {
    private static final String COMMA = ",";
    private static final String COLON = ":";
    private TwitterService service;
    private Scanner coordinateParser;

    @Autowired
    public TwitterCLIRunner(TwitterService service) {
        this.service = service;
    }

    public void run(String[] args) {

        if (args.length < 1) {
            System.err.println(TwitterUtil.SERVICE_USAGE_TEMP);
            return;
        }

        TwitterServiceImp service = new TwitterServiceImp(new TwitterRestDao());
        String cmd = args[0];

        switch (cmd) {
            case "post":
                postTweet(args);
                break;
            case "show":
                showTweet(args);
                break;
            case "delete":
                deleteTweet(args);
                break;
            default:
                System.out.println(TwitterUtil.SERVICE_APP_USAGE);
                break;
        }
    }

    private void showTweet(String[] args) {
        String idStr = args[1];
        if (args.length < 3) {
            System.err.println(TwitterUtil.SERVICE_APP_USAGE);
            return;
        }
        int fields_len = args.length - TwitterUtil.CMD_ARG_INDEX;
        String[] fields = new String[fields_len];
        System.arraycopy(args, TwitterUtil.CMD_ARG_INDEX, fields, 0, fields_len);
        try {
            service.showTweet(idStr, fields);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void deleteTweet(String[] args) {
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
        if (tweets == null) {
            System.err.println("Delete: " + TwitterUtil.INVALID_EX_MSG);
        }
        System.out.println("Successfully deleted: ");
        tweets.forEach(t -> System.out.println(t.getIdStr()));
    }

    private Tweet postTweet(String[] args) {
        String text = args[1];
        coordinateParser = getParser(args);
        Double longitude = coordinateParser.nextDouble();
        Double latitude = coordinateParser.nextDouble();
        Tweet tweet = null;
        try {
            tweet = service.postTweet(text, latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (tweet != null)
            System.out.printf(TwitterUtil.POST_SUCCESS_MSG_FORMAT, tweet.getIdStr());
        return tweet;
    }

    private Scanner getParser(String[] args) {
        if (coordinateParser == null)
            coordinateParser = new Scanner(args[TwitterUtil.CMD_ARG_INDEX]).useDelimiter(COLON);
        return coordinateParser;
    }
}
