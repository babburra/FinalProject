package com.example.yutong.finalproject;
import com.example.yutong.finalproject.structure.course;
import com.example.yutong.finalproject.structure.element.event;
import com.example.yutong.finalproject.structure.element.prio;
import com.example.yutong.finalproject.structure.load;

import org.junit.Test;
import java.util.ArrayList;

import static com.example.yutong.finalproject.structure.utility.dateToString;
import static com.example.yutong.finalproject.structure.utility.getToday;
import static org.junit.Assert.assertTrue;

/**
 * Created by yutong on 11/16/16.
 */

// not saving the file
public class saveTest {
    @Test
    public void rawCourse(){//(String first, String second){
        String first = "cs242 10-4-2016 4";
        String second = "course note";
        course temp = load.addBasicCourse(first,second);
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
