package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.TwitterUtil;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.URIBuilder;
import ca.jrvs.apps.twitter.dto.Coordinates;
import ca.jrvs.apps.twitter.dto.Tweet;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterRestDao implements CrdRepository<Tweet, String>, HttpHelper {

    @Override
    public HttpResponse httpPost(URI uri) throws IOException {
        HttpPost request = new HttpPost(uri.toString());
        TwitterUtil.signRequest(request);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public HttpResponse httpPost(URI uri, StringEntity stringEntity) throws IOException {
        HttpPost request = new HttpPost(uri.toString());
        TwitterUtil.signRequest(request);
        request.setEntity(stringEntity);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public HttpResponse httpGet(URI uri) throws IOException {
        HttpGet request = new HttpGet(uri.toString());
        TwitterUtil.signRequest(request);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
    }

    @Override
    public Tweet findById(String s) throws URISyntaxException, IOException {
        if(TwitterUtil.validateIDStr(s) == false)
            return null;
        // build the request URI
        URI uri = new URI(new URIBuilder().base(TwitterUtil.BASE_URI)
                    .endpoint(TwitterUtil.ENDPOINT_GET).param(TwitterUtil.PARAM_GET, s).toString());
        HttpResponse response = httpGet(uri);
        if(TwitterUtil.checkStatus(response) == false)
            return null;
        // json stream
        InputStream jsonResponse = response.getEntity().getContent();
        return TwitterUtil.toObjectFromJson(jsonResponse, Tweet.class);
    }

    @Override
    public Tweet create(Tweet entity) throws URISyntaxException, IOException {
        if(entity == null) return null;
        Coordinates xy = entity.getCoordinates();
        if(xy == null) xy = new Coordinates();
        URI uri = new URI(new URIBuilder().base(TwitterUtil.BASE_URI)
                .endpoint(TwitterUtil.ENDPOINT_UPDATE)
                .param(TwitterUtil.PARAM_UPDATE, entity.getText())
                .param(TwitterUtil.PARAM_LAT, xy.getLatitudeStr())
                .param(TwitterUtil.PARAM_LONG, xy.getLongitudeStr())
                .toString());
        HttpResponse response = httpPost(uri);
        if (TwitterUtil.checkStatus(response) == false ) {
            InputStream jsonResponse = response.getEntity().getContent();
            new BufferedReader(new InputStreamReader(jsonResponse)).lines().forEach(System.out::println);
            return null;
        }
        InputStream jsonResponse = response.getEntity().getContent();
        return TwitterUtil.toObjectFromJson(jsonResponse, Tweet.class);
    }

    @Override
    public Tweet deleteById(String s) throws URISyntaxException, IOException {
        if(TwitterUtil.validateIDStr(s) == false)
            return null;
        URI uri = new URI(new URIBuilder().base(TwitterUtil.BASE_URI)
            .route(TwitterUtil.ROUTE_DELETE).endpoint(s).toString());
        HttpResponse response = httpPost(uri);
        if (TwitterUtil.checkStatus(response) == false )
            return null;
        InputStream jsonResponse = response.getEntity().getContent();
        return TwitterUtil.toObjectFromJson(jsonResponse, Tweet.class);
    }

}
