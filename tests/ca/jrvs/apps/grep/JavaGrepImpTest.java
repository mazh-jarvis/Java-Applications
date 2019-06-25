package ca.jrvs.apps.grep;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaGrepImpTest {
    private static JavaGrepImp grep;

    @BeforeAll
    public static void setup(){
        System.out.println("Tests are running now!");
        grep = new JavaGrepImp();

    }

    @Test
    void grepCsv() {
        if(grep == null) return;
        grep.setRegex(".+csv$");
        assertTrue(grep.containsPattern("bash_sql/regex/q2:egrep.csv"));
    }

    @Test
    void grepJpeg() {
        grep.setRegex(".+jpe?g$");
        assertTrue(grep.containsPattern("bash_sql/regex/q3:1:abc.jpg"));
    }

    @Test
    void grepIP() {
        grep.setRegex("([0-9]+\\.){3}[0-9]+");
        assertTrue(grep.containsPattern("192.168.100.100"));
    }

    @Test
    void grepEmptyLine() {
        grep.setRegex("^$");
        assertTrue(grep.containsPattern(""));
    }
}
