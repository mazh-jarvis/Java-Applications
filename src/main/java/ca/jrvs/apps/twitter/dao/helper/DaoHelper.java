package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class DaoHelper {

    private static ObjectMapper mapper;

    private DaoHelper() {}

    /**
     * Validate ID string (challenge)
     * @param id URL parameter (id)
     * @return true if id is a valid input
     */
    public static boolean validateIDStr(String id) {
        Stream<Character> charStream = id.chars().mapToObj(c -> (char)c);
        return charStream.allMatch(Character::isDigit);
    }

    /**
     * Convert a java object to JSON string
     * @param object input object
     * @return JSON string
     * @throws JsonProcessingException
     */
    public static String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException {
         /*return getMapper().writer().withDefaultPrettyPrinter()
                .writeValueAsString(object);*/
       return getMapper().writeValueAsString(object);
    }

    /**
     *Parse JSON string to a object
     * @param json JSON str
     * @param _class object class
     * @param <T> type
     * @return object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(String json, Class _class) throws IOException {
        return (T) getMapper().readValue(json, _class);
    }

    /**
     * Parse JSON stream to a object
     * @param jsonIStream JSON stream
     * @param _class object class
     * @param <T> type
     * @return object
     * @throws IOException
     */
    public static <T> T toObjectFromJson(InputStream jsonIStream, Class _class) throws IOException {
        return (T) getMapper().readValue(jsonIStream, _class);
    }

    /**
     * Singleton object mapper
     * initialize and configure if necessary
     * @return singleton object mapper
     */
    private static ObjectMapper getMapper() {
        if(mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return mapper;
    }
}
