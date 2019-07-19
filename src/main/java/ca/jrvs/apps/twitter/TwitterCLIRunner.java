package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Component
public class TwitterCLIRunner {
    private static final String COLON = ":";
    private static final int POST_MIN_ARGS = 3;
    private static final int SHOW_MIN_ARGS = 3;
    private static final int DEL_MIN_ARGS = 2;
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
        if (args.length < SHOW_MIN_ARGS) {
            System.err.println(TwitterUtil.SERVICE_SHOW_USAGE); return;
        }
        int fields_len = args.length - TwitterUtil.CMD_SHOW_ARG_INDEX;
        String[] fields = new String[fields_len];
        System.arraycopy(args, TwitterUtil.CMD_SHOW_ARG_INDEX, fields, 0, fields_len);
        try {
            service.showTweet(idStr, fields);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void deleteTweet(String[] args) {
        if (args.length < DEL_MIN_ARGS) {
            System.err.println(TwitterUtil.SERVICE_DEL_USAGE); return;
        }
        List<Tweet> tweets = null;
        String[] ids = new String[args.length - 1];
        System.arraycopy(args, TwitterUtil.CMD_DEFAULT_ARG_INDEX, ids, 0, ids.length);
        try {
            tweets = service.deleteTweets(ids);
            System.out.println("Successfully deleted: ");
            tweets.forEach(t -> System.out.println(t.getIdStr()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    private Tweet postTweet(String[] args) {
        if (args.length < POST_MIN_ARGS) {
            System.err.println(TwitterUtil.SERVICE_POST_USAGE); return null;
        }
        String text = args[1];
        coordinateParser = getCoordinateParser(args);
        Tweet tweet = null;
        try {
            Double longitude = coordinateParser.nextDouble();
            Double latitude = coordinateParser.nextDouble();
            tweet = service.postTweet(text, latitude, longitude);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.err.println(TwitterUtil.INVALID_GEO_FORMAT);
        }
        if (tweet != null)
            System.out.printf(TwitterUtil.POST_SUCCESS_MSG_FORMAT, tweet.getIdStr());
        return tweet;
    }

    private Scanner getCoordinateParser(String[] args) {
        if (coordinateParser == null)
            coordinateParser = new Scanner(args[TwitterUtil.CMD_DEFAULT_ARG_INDEX +1]);
        coordinateParser.useDelimiter(COLON);
        return coordinateParser;
    }
}
