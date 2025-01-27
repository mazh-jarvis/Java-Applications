package ca.jrvs.apps.grep;

import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class JavaGrepImpTest {
    private static JavaGrepImp grep;

    @BeforeClass
    public static void setup(){
        System.out.println("Tests are running now!");
        grep = new JavaGrepImp();
    }

    @Test
    public void grepCsv() {
        if(grep == null) return;
        grep.setRegex(".+csv$");
        assertTrue(grep.containsPattern("bash_sql/regex/q2:egrep.csv"));
    }

    @Test
    public void grepJpeg() {
        grep.setRegex(".+jpe?g$");
        assertTrue(grep.containsPattern("bash_sql/regex/q3:1:abc.jpg"));
    }

    @Test
    public void grepIP() {
        grep.setRegex("([0-9]{1,3}\\.){3}[0-9]{1,3}");
        assertTrue(grep.containsPattern("192.168.10.100"));
    }

    @Test
    public void grepEmptyLine() {
        grep.setRegex("^$");
        assertTrue(grep.containsPattern(""));
    }

}
