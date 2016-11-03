package test.Info.element;

import org.junit.Before;
import org.junit.Test;
import work.Info.element.date;
import work.Info.element.event;
import work.Info.element.prio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ting huang on 11/2/2016.
 */
public class eventTest {
    private event test;
    @Before
    public void setup(){
        this.test = new event(new date(1,2,3),"name","info",prio.high);
    }
    @Test
    public void checkInfo(){
        assertTrue(test.getInfo().equals("info"));
    }
    @Test
    public void checkName(){
        assertTrue(test.getName().equals("name"));
    }
    @Test
    public void checkPrio(){
        assertTrue(test.getPrio() == prio.high);
    }
    @Test
    public void checkDate(){
        assertTrue(test.getDate().getDay() == 2);
        assertTrue(test.getDate().getMonth() == 1);
        assertTrue(test.getDate().getYear() == 3);
    }
    @Test
    public void testEqual(){
        event test1 = new event(new date(1,2,3),"name","info",prio.high);
        event test2 = new event(new date(1,2,3),"name","info",prio.low);
        assertTrue(test.compareTo(test1));
        assertFalse(test.compareTo(test2));
    }

}
