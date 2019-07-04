package ca.jrvs.apps.twitter.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface CrdRepository<T, ID> {
    T save(T entity) throws URISyntaxException, IOException;
    T findById(ID id) throws IOException, URISyntaxException;
    T deleteById(ID id) throws URISyntaxException, IOException;
}
