package ca.jrvs.apps.twitter.dao;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TwitterRestDaoTest {

    private static TwitterRestDao dao;

    @BeforeClass
    public static void setup() {
        dao = new TwitterRestDao();
    }

    @Test
    public void idTest1(){
        assertTrue(dao.validateIDStr("59175"));
    }

    @Test
    public void idTest2(){
        assertFalse(dao.validateIDStr("30d95"));
    }
}
