package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class DaoHelper {
    // Validate ID string challenge
    public static boolean validateIDStr(String id) {
        Stream<Character> charStream = id.chars().mapToObj(c -> (char)c);
        return charStream.allMatch(Character::isDigit);
    }



    public static <T> T toObjectFromJson(String json, Class _class) throws IOException {
        // json object mapper setup
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return (T) objectMapper.readValue(json, _class);
    }


    public static <T> T toObjectFromJson(InputStream jsonIStream, Class _class) throws IOException {
        // json object mapper setup
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return (T) objectMapper.readValue(jsonIStream, _class);
    }
}
