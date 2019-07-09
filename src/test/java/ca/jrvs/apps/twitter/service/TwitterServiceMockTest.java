package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static junit.framework.TestCase.*;

//import org.junit
@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceMockTest {
    @InjectMocks
    private TwitterServiceImp service;
    @Mock
    private CrdRepository mockDao;

    @Test
    public void postTweet() {
        String tweetText = "This is a fake tweet!";
        Tweet mockTweet = new Tweet(tweetText);
        try {
            when(mockDao.create(any())).thenReturn(mockTweet);
            Tweet result = service.postTweet("some tweet", 0.0, 0.0);
            assertEquals(result.getText(), mockTweet.getText());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*@Test
    public void deleteTweet() {
        Tweet mockTweet = new Tweet("Fake tweet");

    }*/
}
