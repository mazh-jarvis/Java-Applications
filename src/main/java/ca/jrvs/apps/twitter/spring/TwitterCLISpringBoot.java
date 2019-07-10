package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.TwitterCLIRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TwitterCLISpringBoot implements CommandLineRunner {

    private TwitterCLIRunner runner;

    @Autowired
    public TwitterCLISpringBoot(TwitterCLIRunner runner) {
        this.runner = runner;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ca.jrvs.apps.twitter.spring.TwitterCLIBean.class);
        TwitterCLIRunner app = context.getBean(TwitterCLIRunner.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        runner.run(args);
    }
}
