package test.Info.element;

import org.junit.Before;
import org.junit.Test;
import work.Info.element.note;

import static org.junit.Assert.assertTrue;

/**
 * Created by ting huang on 11/2/2016.
 */
public class noteTest {
    private note test;

    @Before
    public void setUp(){
        test = new note("i hate this game");
    }
    @Test
    public void testNote(){
        assertTrue(test.getNote().equals("i hate this game"));
    }
}
