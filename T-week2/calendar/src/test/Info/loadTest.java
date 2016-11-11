package test.Info;

import org.junit.Test;
import work.Info.course;
import work.Info.element.event;
import work.Info.element.note;
import work.Info.element.prio;
import work.Info.unit;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static work.Info.load.loadCourses;
import static work.Info.load.loadFileAll;
import static work.Info.save.saveFileAll;
import static work.Info.utility.eventToString;
import static work.Info.utility.getToday;

/**
 * Created by ting huang on 11/11/2016.
 */
public class loadTest {
    public unit unit;
    @Test
    public void setUp(){
        unit unit = new unit();
        event event = new event(getToday(),"name","info", prio.high);
        note note = new note("this is new note");
        ArrayList<event> asdf = new ArrayList<event>();
        asdf.add(event);
        course course = new course(4,"cs242",asdf,"course note");
        unit.addCouse(course);
        unit.addEvent(event);
        unit.addNote(note);
        saveFileAll(unit);
    }
    @Test
    public void checkEvent(){
        unit unit = loadFileAll();
        ArrayList<event> events = unit.getEvents();
        assertTrue(eventToString(events.remove(0)).equals("10-11-2016|name|info|high"));
    }
    @Test
    public void checkCourse(){
        ArrayList<course> course= loadCourses();
        course temp = course.remove(0);
        assertTrue(eventToString(temp.getEvents().remove(0)).equals("10-11-2016|name|info|high"));
        assertTrue(temp.getName().equals("cs242"));
        assertTrue(temp.getNote().getNote().equals("course note"));
    }
}
