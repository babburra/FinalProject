package com.example.yutong.finalproject.structure;

import android.content.Context;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Formatter;

import com.example.yutong.finalproject.structure.element.*;

import static com.example.yutong.finalproject.structure.utility.*;
/**
 * Created by ting huang on 11/3/2016.
 */
public class save {
    public static Formatter save;

    public static void saveFile(String name){
        try {
            save = new Formatter("/home/yutong/AndroidStudioProjects/FinalProject/app/src/main/java/com/example/yutong/finalproject/structure/" + name);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *  saving current unit into three file                                     #note week 2
     *  save fill all is main function that
     *  act like a wrapper. However, individual save
     *  can be perform.
     */
    public static void saveFileAll(unit unit){
        saveFileCourses(unit.getCourses());
        saveFileEvents(unit.getEvents());
        saveFileNotes(unit.getNotes());
    }
    public static void saveFileEvents(ArrayList<event> unit){
        saveFile("events");
        saveEvent(unit);
        save.close();
    }
    public static void saveFileNotes(ArrayList<note> unit){
        saveFile("notes");
        saveNote(unit);
        save.close();
    }
    public static void saveFileCourses(ArrayList<course> unit){
        saveFile("courses");
        saveCourse(unit);
        save.close();
    }

    /**
     * taking Array list of course and parse into the format that is readable           #note week 2
     * for load function.Same will be apply saveEvent, and saveNote. However,
     * data structure for note in the future week might change, delete rather.
     */
    public static void saveCourse(ArrayList<course> courses){
        for(course course: courses){
            Integer credit = course.getCredit();
            date registerd = course.getRegistered();
            save.format("%s %s %s\n",course.getName(),dateToString(registerd),
                    credit.toString());
            save.format("%s\n", course.getNote().getNote());
            //for event
            ArrayList<event> courseEvent = course.getEvents();
            saveArrayEvent(courseEvent);
            save.format("%s\n","another");
        }
    }
    public static void saveEvent(ArrayList<event> events){
        saveArrayEvent(events);
    }
    public static void saveArrayEvent(ArrayList<event> events){
        if(events!=null){
            for(event event: events){
                save.format("%s%s%s%s\n",dateToString(event.getDate())+"|",
                        event.getName()+"|",event.getInfo()+"|",event.getPrio().toString());
            }
        }else{
            save.format("%s\n","course had no event");
        }
    }
    public static void saveNote(ArrayList<note> notes){
        if(notes!=null){
            for(note note: notes){
                save.format("%s\n",note.getNote());
            }
        }else{
            save.format("%s\n", "course had no note");
        }
    }
}
