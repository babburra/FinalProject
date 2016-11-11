package com.example.yutong.finalproject.structure;

import com.example.yutong.finalproject.structure.element.*;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import static com.example.yutong.finalproject.structure.utility.*;

/**
 * Created by ting huang on 11/3/2016.
 */
public class fileData {
    private String fileName = "user";
    private Formatter save;
    private Scanner read;

    // separate file
    @Test
    public void main(){
        unit unit = new unit();
        event event = new event(getToday(),"name","info", prio.high);
        note note = new note("this is new noteActivity");
        ArrayList<event> asdf = new ArrayList<event>();
        asdf.add(event);
        course course = new course(4,"cs242",asdf,"courseActivity noteActivity");
        unit.addCouse(course);
        unit.addEvent(event);
        unit.addNote(note);
        save(unit, fileName);
        load(fileName);
    }

    public void save(unit unit, String fileName){
        try {
            this.save = new Formatter(fileName);
        } catch (Exception e) {
            System.out.println("ooops");
        }
        saveCourse(unit.getCourses());
        saveEvent(unit.getEvents());
        saveNote(unit.getNotes());
        save.close();
    }
    public void saveCourse(ArrayList<course> courses){
        for(course course: courses){
            Integer credit = course.getCredit();
            date registerd = course.getRegistered();
            save.format("%s\n",course.getName(),dateToString(registerd),
                    credit.toString());
            save.format("%s\n", course.getNote());
            //for event
            ArrayList<event> courseEvent = course.getEvents();
            saveArrayEvent(courseEvent);
            save.format("%s\n","another");
        }
        save.format("%s","CL-e\n");
    }
    public void saveEvent(ArrayList<event> events){
        saveArrayEvent(events);
        save.format("%s\n","EL-e");
    }
    public void saveArrayEvent(ArrayList<event> events){
        if(events!=null){
            for(event event: events){
                save.format("%s%s%s%s\n",dateToString(event.getDate())+"|",
                        event.getName()+"|",event.getInfo()+"|",event.getPrio().toString());
            }
        }else{
            save.format("%s\n","courseActivity had no event");
        }
    }
    public void saveNote(ArrayList<note> notes){
        if(notes!=null){
            for(note note: notes){
                save.format("%s\n",note.getNote());
            }
        }else{
            save.format("%s\n", "courseActivity had no noteActivity");
        }
        save.format("%s\n","NL-e");
    }

    //Todo finish load later.
    public unit load(String name) {
        try {
            read = new Scanner(new File(name));
        } catch (Exception e) {
            System.out.println("ooops");
            return null;
        }
        if (!read.hasNext()) return null;

        ArrayList<event> events;
        ArrayList<course> courses;
        ArrayList<note> notes = new ArrayList<>();

        //adding courses
        ArrayList<String> raw = new ArrayList<>();
        for(String temp = read.nextLine(); !temp.equals("CL-e"); temp = read.nextLine()) {
            raw.add(temp);
        }
        courses = rawCourse(raw);

        //adding event list
        raw = new ArrayList<>();
        for(String temp = read.nextLine(); !temp.equals("EL-e"); temp = read.nextLine()) {
            raw.add(temp);
        }
        events = rawEvent(raw);

        //adding noteActivity
        for(String temp = read.nextLine();!temp.equals("NL-e");temp = read.nextLine()){
            notes.add(new note(temp));
        }
        return new unit(courses, events, notes);
    }
    //TODO !!!!!!!!!!!!!! this reuiqred testing!
    public ArrayList<course> rawCourse(ArrayList<String> raw){
        ArrayList<course> courses = new ArrayList<course>();
        int index = 0;
        while(index<= raw.size()){
            course course = addRawCourse(raw.get(index),raw.get(index+1));
            ArrayList<event> events = new ArrayList<>();
            index+=2;
            String temp = raw.get(index);
            index++;
            for(;!temp.equals("another");index++){
                event event = stringToEvent(temp);
                temp = raw.get(index);
                events.add(event);
            }
            course.changeEvents(events);
        }
        return courses;
    }
    //TODO !!!!!!!!!!!! this required testing!
    public ArrayList<event> rawEvent(ArrayList<String> raw){
        ArrayList<event> events = new ArrayList<>();
        for(String temp: raw){
            events.add(stringToEvent(temp));
        }
        return events;
    }
    public static course addRawCourse(String first, String second){
        String[] segment1 = first.split(" ");
        String[] date = segment1[1].split("-");
        ArrayList<event> events = new ArrayList<event>();
        events.add(new event(getToday(),"name","info", prio.high));
        return new course(Integer.parseInt(segment1[2]),segment1[0],
                new date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])),
                events,second);
    }
}
