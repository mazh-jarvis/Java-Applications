package ca.jrvs.apps.twitter.dao;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface CrdRepository<T, ID> {
    /**
     * Post a twitter status
     * @param entity
     * @return posted tweet
     * @throws URISyntaxException
     * @throws IOException
     */
    T save(T entity) throws URISyntaxException, IOException;

    /**
     * Show a twitter status
     * @param id
     * @return found tweet
     * @throws IOException
     * @throws URISyntaxException
     */
    T findById(ID id) throws IOException, URISyntaxException;

    /**
     * Delete a twitter status
     * @param id
     * @return deleted tweet
     * @throws URISyntaxException
     * @throws IOException
     */
    T deleteById(ID id) throws URISyntaxException, IOException;
}
