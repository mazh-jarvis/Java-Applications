package ca.jrvs.apps.grep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaGrepImpTest {
    private JavaGrepImp grep;
    @Test
    void process() {
    }

    @Test
    void matchCsv() {
        assertTrue(grep.containsPattern("csv"));
        assertTrue(grep.containsPattern("jpe?g"));
        assertTrue(grep.containsPattern("([0-9]+\\.){3}[0-9]"));
        assertTrue(grep.containsPattern("^$"));
    }
}