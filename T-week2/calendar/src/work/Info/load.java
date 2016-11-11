package work.Info;

import org.junit.Test;
import work.Info.element.date;
import work.Info.element.event;
import work.Info.element.note;
import work.Info.element.prio;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static work.Info.utility.getToday;
import static work.Info.utility.stringToEvent;

/**
 * Created by ting huang on 11/9/2016.
 */
public class load {

    private static Scanner read;

    /**
     * open up file for later operation.                                                #note week 2
     * @param name the file that we want to open
     * @return  all the string in the form of array list for later operation
     */
    private static ArrayList<String> loadFile(String name){
        try {
            read = new Scanner(new File(name));
        } catch (Exception e) {
            System.out.println("ooops");
        }
        ArrayList<String> temp = new ArrayList<>();
        while(read.hasNextLine()){
            String source = read.nextLine();
            temp.add(source);
        }
        return temp;
    }

    /**
     * load all three of the file
     * @return whole unit variable back to the main board                               #note week 2
     */
    public static unit loadFileAll() {
        return new unit(loadCourses(),loadEvent(),loadNotes());
    }

    /**
     * loading up course file and getting inside information in the form                #note week 2
     * of Array list of course back to caller.
     */
    public static ArrayList<course> loadCourses(){
        ArrayList<course> courses = new ArrayList<course>();
        ArrayList<String> raw = loadFile("courses");
        int index = 0;
        while(index < raw.size()){
            course course = addBasicCourse(raw.get(index),raw.get(index+1));
            index+=2;
            ArrayList<event> events = new ArrayList<>();
            for(String temp = "";;){
                temp = raw.get(index);
                index++;
                if(temp.equals("another")) break;
                event eventHolder = stringToEvent(temp);
                events.add(eventHolder);
            }
            course.setEvents(events);
            courses.add(course);
        }
        return courses;
    }

    /**
     *  helper function that help loadCourse to set up the course information               #note week 2
     *  this part is some what hard coded, since we are always expecting to have
     *  the format that we are looking for
     */
    public static course addBasicCourse(String first, String second){
        String[] segment1 = first.split("\\s+");
        String[] date = segment1[1].split("-");
        ArrayList<event> events = new ArrayList<event>();
        events.add(new event(getToday(),"name","info", prio.high));
        return new course(Integer.parseInt(segment1[2]),segment1[0],
                new date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])),
                events,second);
    }

    /**
     * express the same idea as the previous function of load course.                   #note week 2
     * but in comparison this function had less line of operation.
     */
    public static ArrayList<event> loadEvent(){
        ArrayList<event> events = new ArrayList<>();
        ArrayList<String> raw = loadFile("events");
        for(String temp: raw){
            events.add(stringToEvent(temp));
        }
        return events;
    }

    /**
     * this function might be change later on.                                            #note week 2
     */
    public static ArrayList<note> loadNotes(){
        ArrayList<note> notes = new ArrayList<>();
        ArrayList<String> raw = loadFile("notes");
        for(String temp: raw){
            notes.add(new note(temp));
        }
        return notes;
    }
}
