package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CrdRepository<T, ID> {
    T save(T entity);
    T findById(ID id) throws IOException, URISyntaxException;
    T deleteById(ID id);
}
