package test.Info;

import org.junit.Test;
import work.Info.element.date;
import work.Info.element.event;
import work.Info.element.prio;

import static org.junit.Assert.assertTrue;
import static work.Info.utility.*;

/**
 * Created by ting huang on 11/4/2016.
 */
public class utilityTest {
    private date day = new date(6,19,1994);
    private String string = "10-4-2016|name|info|high";
    private event event = new event(new date(10,4,2016),"name","info",prio.high);
    private event even = new event(day,"name","info", prio.high);
    @Test
    public void testDateToString(){
        assertTrue(dateToString(day).equals("6-19-1994"));
    }
    @Test
    public void testStringToDate(){
        assertTrue(stringToDate("6-19-1994").equals(day));
    }
    @Test
    public void testStringToEvent(){
        assertTrue(stringToEvent(string).compareTo(event));
    }
    @Test
    public void testEventToString(){
        assertTrue(eventToString(event).equals(string));
    }
}
