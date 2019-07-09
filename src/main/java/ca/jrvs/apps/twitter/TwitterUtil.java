package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.Keys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.params.CookieSpecPNames;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.stream.Stream;

public class TwitterUtil {

    public static final int HTTP_OK = 200;
    public static final String BASE_URI = "https://api.twitter.com/1.1/statuses";
    public static final String ENDPOINT_GET = "show";
    public static final String ENDPOINT_UPDATE = "update";
    public static final String ROUTE_DELETE = "destroy";
    public static final String PARAM_GET = "id";
    public static final String PARAM_UPDATE = "status";
    public static final String PARAM_LAT = "lat";
    public static final String PARAM_LONG = "long";
    // service constants
    public static final String INVALID_EX_MSG = "Received a tweet with no content";
    public static final double MAX_LAT = 90;
    public static final double MAX_LONG = 180;

    public static final String SERVICE_USAGE_TEMP = "Usage: java -jar java_apps.jar";
    public static final String SERVICE_APP_USAGE = SERVICE_USAGE_TEMP + " <command> <args>";
    public static final String SERVICE_POST_USAGE = SERVICE_USAGE_TEMP + " post <status-text>";
    public static final String SERVICE_SHOW_USAGE = SERVICE_USAGE_TEMP + " show <id> <field..>";
    public static final String SERVICE_DEL_USAGE = SERVICE_USAGE_TEMP + " delete <id..>";

    public static final String COOKIE_DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss z";
    public static final String POST_SUCCESS_MSG_FORMAT = "# Success: a new tweet was posted (id: %s)\n";

    public static final int CMD_ARG_INDEX = 2;
    private static final int MAX_TWEET_LEN = 150;
    // Json object mapper singleton
    private static ObjectMapper mapper;

    // class not instantiable
    private TwitterUtil() {}

    /**
     * Validate post tweet
     * @param text tweet text
     * @param longitude
     * @param latitude
     */
    public static void validatePostTweet(String text, Double longitude, Double latitude) {
        if (text.length() > MAX_TWEET_LEN)
            throw new InvalidParameterException("Maximum tweet length is 150 characters");
        if (Math.abs(latitude) >  TwitterUtil.MAX_LAT
                || Math.abs(longitude) > TwitterUtil.MAX_LONG)
            throw new InvalidParameterException("Invalid coordinates");
    }

    /**
     * Validate ID string (challenge)
     * @param id URL parameter (id)
     * @return true if id is a valid input
     */
    public static boolean validateIDStr(String id) {
        Stream<Character> charStream = id.chars().mapToObj(c -> (char)c);
        boolean result = charStream.allMatch(Character::isDigit);
        if(!result)
            System.err.println("# ERROR: Invalid ID format!");
        return result;
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

    /**
     * Signs an http request
     * @param request http request
     */
    public static void signRequest(HttpRequestBase request) {
        OAuthConsumer consumer =
                new CommonsHttpOAuthConsumer(Keys.CONSUMER_KEY, Keys.CONSUMER_SECRET);
        // assign access tokens
        consumer.setTokenWithSecret(Keys.ACCESS_TOKEN, Keys.TOKEN_SECRET);
        // sign the request
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks http status
     * @param response server response
     * @return true if status was OK
     */
    public static boolean checkStatus(HttpResponse response) {
        int status = response.getStatusLine().getStatusCode();
        if(status != TwitterUtil.HTTP_OK) {
            System.err.println("Server responded with error " + status);
            return false;
        }
        return true;
    }

    public static void setRequestHeaders(HttpRequestBase request) {
        request.getParams().setParameter(CookieSpecPNames.DATE_PATTERNS, Arrays.asList(COOKIE_DATE_FORMAT));
    }
}
