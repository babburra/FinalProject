package test.Info;

import org.junit.Test;
import work.Info.course;
import work.Info.element.date;
import work.Info.element.event;
import work.Info.element.prio;
import work.Info.fileData;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static work.Info.utility.dateToString;
import static work.Info.utility.getToday;

/**
 * Created by ting huang on 11/4/2016.
 */
public class fileDataTest {
    @Test
    public void rawCourse(){//(String first, String second){
        String first = "cs242 10-4-2016 4";
        String second = "course note";
        course temp = fileData.addRawCourse(first,second);
        assertTrue(temp.getName().equals("cs242"));
        assertTrue(temp.getCredit() == 4);
        assertTrue(dateToString(temp.getRegistered()).equals("10-4-2016"));
        assertTrue(temp.getNote().getNote().equals("course note"));

        ArrayList<event> single = temp.getEvents();
        for(event temp1: single){
            assertTrue(temp1.getDate().equals(getToday()));
            assertTrue(temp1.getName().equals("name"));
            assertTrue(temp1.getInfo().equals("info"));
            assertTrue(temp1.getPrio().equals(prio.high));
        }
    }
}
