package ca.jrvs.apps.twitter.dao.helper;

import java.util.stream.Stream;

public class DaoHelper {
    // Validate ID string challenge
    public static boolean validateIDStr(String id) {
        Stream<Character> charStream = id.chars().mapToObj(c -> (char)c);
        return charStream.allMatch(Character::isDigit);
    }
}
