package work.Info;

import work.Info.element.event;
import work.Info.element.note;

import java.util.ArrayList;

/**
 * Created by ting huang on 11/3/2016.
 */
public class unit {
    private ArrayList<course> courses;
    private ArrayList<event> events;
    private ArrayList<note> notes;

    /**
     * this is the default constructor for the unit class                            # note week1
     * most of the stuff set to empty
     */
    public unit(){
        this.courses = new ArrayList<course>();
        this.events = new ArrayList<event>();
        this.notes = new ArrayList<note>();
    }
    public ArrayList<course> getCourses(){
        return courses;
    }
    public ArrayList<event> getEvents(){
        return events;
    }
    public ArrayList<note> getNotes(){
        return notes;
    }
    /**
     * this constructor will initialize the data inside of the structure base on         # note week1
     * input from the parsing functions, but so far we had no parsing for this week
     */
    public unit(ArrayList<course> courses, ArrayList<event> events, ArrayList<note> notes){
        this.courses = courses;
        this.events = events;
        this.notes = notes;
    }
    /**
     * adding note to the unit, adjusting the size of the note array                #note week1
     * this function mostly are moving around the data in the array
     */
    public void addNote(note note){
        this.notes.add(note);
    }
    public void deleteNote(note note){
        this.notes.remove(note);
    }
    /**
     * adding course to the unit, adjusting the size of the course array             #note week1
     * this function will do similar ability as addNote
     */
    public void addCouse(course course){
        this.courses.add(course);
    }
    public void deleteCourse(course course){
        this.courses.remove(course);
    }
    /**
     * same stuff as addCourse and addNote, but this added event will not               #note week1
     * appear in course event, this event is more for global event
     */
    public void addEvent(event event){
        this.events.add(event);
    }
    public void deleteEvent(event event){
        this.events.remove(event);
    }

}
