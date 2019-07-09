package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterCLIBean {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);
    }

    @Bean
    public TwitterService twitterService(CrdRepository dao){
        return new TwitterServiceImp(dao);
    }

    @Bean
    public TwitterCLIRunner twitterCLIRunner(TwitterService service) {
        return new TwitterCLIRunner(service);
    }

    @Bean
    public CrdRepository twitterDao() {
        return new TwitterRestDao();
    }
}
