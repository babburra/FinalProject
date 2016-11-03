package work.Info;

import work.Info.element.event;
import work.Info.element.note;

/**
 * Created by ting huang on 11/3/2016.
 */
public class unit {
    private course[] courses;
    private event[] events;
    private note[] notes;
    private int numCourse;
    private int numEvent;
    private int numNotes;

    /**
     * this is the defaul constructor for the unit class                            # note week1
     * most of the stuff set to empty
     */
    public unit(){
        this.courses = null;
        this.events = null;
        this.notes = null;
        this.numCourse = 0;
        this.numEvent = 0;
        this.numNotes = 0;
    }

    /**
     * this constructor will initialize the data inside of the structure base on         # note week1
     * input from the parsing functions, but so far we had no parsing for this week
     */
    public unit(course[] courses, event[] events, note[] notes){
        this.courses = courses;
        this.events = events;
        this.notes = notes;
        this.numCourse = courses.length;
        this.numEvent = events.length;
        this.numNotes = notes.length;
    }

    /**
     * adding note to the unit, adjusting the size of the note array                #note week1
     * this function mostly are moving around the data in the array
     */
    public void addNote(note note){
        note[] copy = new note[this.numNotes +1];
        for(int i = 0; i<this.numNotes; i++){
            copy[i] = this.notes[i];
        }
        copy[numNotes] = note;
        this.numNotes+=1;
        this.notes = copy;
    }
    //TODO deleteNote
    /**
     * adding course to the unit, adjusting the size of the course array             #note week1
     * this function will do similar ability as addNote
     */
    public void addCouse(course course){
        course[] copy = new course[this.numCourse+1];
        for(int i = 0; i<this.numCourse; i++){
            copy[i] = this.courses[i];
        }
        copy[numNotes] = course;
        this.numNotes+=1;
        this.courses = copy;
    }
    //TODO deleteCourse
    /**
     * same stuff as addCourse and addNote, but this added event will not               #note week1
     * appear in course event, this event is more for global event
     */
    public void addEvent(event event){
        event[] copy = new event[this.numEvent+1];
        for(int i = 0; i<this.numEvent; i++){
            copy[i] = this.events[i];
        }
        copy[numNotes] = event;
        this.numNotes+=1;
        this.events = copy;
    }
    //TODO deleteEvent

}
