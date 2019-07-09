package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "ca.jrvs.apps.twitter")
@Configuration
public class TwitterCLIComponentScan {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ca.jrvs.apps.twitter.spring.TwitterCLIBean.class);
        TwitterCLIRunner runner = context.getBean(TwitterCLIRunner.class);
        runner.run(args);
    }
}

