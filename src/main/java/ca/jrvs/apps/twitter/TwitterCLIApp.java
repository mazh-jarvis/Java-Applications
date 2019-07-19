package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLIApp {

    public static void main(String[] args) {
        CrdRepository dao = new TwitterRestDao();
        TwitterService service = new TwitterServiceImp(dao);
        TwitterCLIRunner runner = new TwitterCLIRunner(service);
        runner.run(args);
    }
}
