package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.DaoHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.URIBuilder;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterRestDao implements CrdRepository<Tweet, String>, HttpHelper {
    public static final String BASE_URI = "https://api.twitter.com/1.1/statuses";

    @Override
    public HttpResponse httpPost(URI uri) {
        return null;
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) throws IOException {
        HttpPost request = new HttpPost(uri.toString());
        DaoHelper.signRequest(request);
        request.setEntity(stringEntity);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public HttpResponse httpGet(URI uri) throws IOException {
        HttpGet request = new HttpGet(uri.toString());
        DaoHelper.signRequest(request);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public Tweet findById(String s) throws URISyntaxException, IOException {
        if(DaoHelper.validateIDStr(s) == false) {
            System.err.println("# ERROR: Invalid ID format!");
            return null;
        }
        // build the request URI
        URI uri = new URI(new URIBuilder().base(BASE_URI)
                        .endpoint("show").param("id", s).toString());
        HttpResponse response = httpGet(uri);
        int status = response.getStatusLine().getStatusCode();
        if(status != 200) {
            System.err.println("Server responded with error " + status);
            return null;
        }
        // json stream
        InputStream jsonIn = response.getEntity().getContent();
        return DaoHelper.toObjectFromJson(jsonIn, Tweet.class);
    }

    @Override
    public Tweet save(Tweet entity) throws URISyntaxException {
        URI uri = new URI(new URIBuilder().base(BASE_URI)
                .endpoint("update").toString());

        return null;
    }

    @Override
    public Tweet deleteById(String s) {
        return null;
    }

    private String getShowUri() {
        return null;
    }
}
