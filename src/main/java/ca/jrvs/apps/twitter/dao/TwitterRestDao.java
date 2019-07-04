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

    @Override
    public HttpResponse httpPost(URI uri) throws IOException {
        HttpPost request = new HttpPost(uri.toString());
        DaoHelper.signRequest(request);
        HttpClient client = new DefaultHttpClient();
        return client.execute(request);
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
        if(DaoHelper.validateIDStr(s) == false)
            return null;
        // build the request URI
        URI uri = new URI(new URIBuilder().base(DaoHelper.BASE_URI)
                    .endpoint(DaoHelper.ENDPOINT_GET).param(DaoHelper.PARAM_GET, s).toString());
        HttpResponse response = httpGet(uri);
        if(DaoHelper.checkStatus(response) == false)
            return null;
        // json stream
        InputStream jsonResponse = response.getEntity().getContent();
        return DaoHelper.toObjectFromJson(jsonResponse, Tweet.class);
    }

    @Override
    public Tweet save(Tweet entity) throws URISyntaxException, IOException {
        if(entity == null) return null;
        URI uri = new URI(new URIBuilder().base(DaoHelper.BASE_URI)
                .endpoint(DaoHelper.ENDPOINT_UPDATE)
                .param(DaoHelper.PARAM_UPDATE, entity.getText())
                .toString());
        HttpResponse response = httpPost(uri);
        if (DaoHelper.checkStatus(response) == false )
            return null;
        InputStream jsonResponse = response.getEntity().getContent();
        return DaoHelper.toObjectFromJson(jsonResponse, Tweet.class);
    }

    @Override
    public Tweet deleteById(String s) throws URISyntaxException, IOException {
        if(DaoHelper.validateIDStr(s) == false)
            return null;
        URI uri = new URI(new URIBuilder().base(DaoHelper.BASE_URI)
            .route(DaoHelper.ROUTE_DELETE)
            .endpoint(s)
            .toString());
        HttpResponse response = httpPost(uri);
        if (DaoHelper.checkStatus(response) == false )
            return null;
        InputStream jsonResponse = response.getEntity().getContent();
        return DaoHelper.toObjectFromJson(jsonResponse, Tweet.class);
    }

}
