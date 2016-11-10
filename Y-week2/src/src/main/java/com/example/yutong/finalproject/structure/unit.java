package com.example.yutong.finalproject.structure;

import com.example.yutong.finalproject.structure.element.*;

import java.util.ArrayList;

/**
 * Created by ting huang on 11/3/2016.
 */
public class unit {
    private ArrayList<course> courses;
    private ArrayList<event> events;
    private ArrayList<note> notes;

    /**
     * this is the defaul constructor for the unit class                            # noteActivity week1
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
     * this constructor will initialize the data inside of the structure base on         # noteActivity week1
     * input from the parsing functions, but so far we had no parsing for this week
     */
    public unit(ArrayList<course> courses, ArrayList<event> events, ArrayList<note> notes){
        this.courses = courses;
        this.events = events;
        this.notes = notes;
    }
    /**
     * adding noteActivity to the unit, adjusting the size of the noteActivity array                #noteActivity week1
     * this function mostly are moving around the data in the array
     */
    public void addNote(note note){
        this.notes.add(note);
    }
    public void deleteNote(note note){
        this.notes.remove(note);
    }
    //TODO deleteNote
    /**
     * adding courseActivity to the unit, adjusting the size of the courseActivity array             #noteActivity week1
     * this function will do similar ability as addNote
     */
    public void addCouse(course course){
        this.courses.add(course);
    }
    public void deleteCourse(course course){
        this.courses.remove(course);
    }
    //TODO deleteCourse
    /**
     * same stuff as addCourse and addNote, but this added event will not               #noteActivity week1
     * appear in courseActivity event, this event is more for global event
     */
    public void addEvent(event event){
        this.events.add(event);
    }
    public void deleteEvent(event event){
        this.events.remove(event);
    }

}
