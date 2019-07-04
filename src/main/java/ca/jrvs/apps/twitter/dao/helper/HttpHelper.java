package ca.jrvs.apps.twitter.dao.helper;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;

public interface HttpHelper {
    HttpResponse httpPost(URI uri) throws IOException;
    HttpResponse httpPost(URI uri, StringEntity stringEntity) throws IOException;
    HttpResponse httpGet(URI uri) throws IOException;
}
