package test.Info.element;
import org.junit.Before;
import org.junit.Test;
import work.Info.element.date;

import static org.junit.Assert.*;
/**
 * Created by ting huang on 11/2/2016.
 */
public class dateTest {
    private date test;

    @Before
    public void setUp(){
        test = new date(1,2,1994);
    }

    @Test
    public void month(){
        assertTrue(test.getMonth() == 1);
    }
    @Test
    public void date(){
        assertTrue(test.getDay() == 2);
    }
    @Test
    public void year(){
        assertTrue(test.getYear() == 1994);
    }
    @Test
    public void testEqual(){
        date test1 = new date(1,2,3);
        date test2 = new date(1,2,3);
        date test3 = new date(1,2,1);
        assertTrue(test1.equals(test2));
        assertFalse(test1.equals(test3));
    }
}
