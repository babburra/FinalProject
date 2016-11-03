package test.Info;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import work.Info.course;
import work.Info.element.date;
import work.Info.utility;

import static org.junit.Assert.assertTrue;

/**
 * Created by ting huang on 11/2/2016.
 */
public class courseTest {
    private course test;
    private course test1;
    @Before
    public void setUp(){
        test = new course(4,"course",null, "note");
        test1 = new course(4,"course",null,null);
    }
    @Test
    public void testCredit(){
        assertTrue(test.getCredit()==4);
    }
    @Test
    public void testName(){
        assertTrue(test.getName().equals("course"));
    }
    @Test
    public void testRegi(){
        date temp = utility.getToday();
        assertTrue(test.getRegistered().getDay()==temp.getDay());
        assertTrue(test.getRegistered().getMonth()==temp.getMonth());
        assertTrue(test.getRegistered().getYear()==temp.getYear());
        assertTrue(test1.getRegistered().getDay()==temp.getDay());
        assertTrue(test1.getRegistered().getMonth()==temp.getMonth());
        assertTrue(test1.getRegistered().getYear()==temp.getYear());
    }
    @Test
    public void testNote() {
        assertTrue(test.getNote().getNote().equals("note"));
        assertTrue(test1.getNote().getNote().equals("Empty"));
    }
    // TODO: 11/2/2016  required more specific testing for this one
    // TODO: hence we are skipping this for now.
    @Ignore
    @Test
    public void testEvent(){
        assertTrue(test.getEvents().equals(null));
    }
}
