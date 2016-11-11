package work.Info;
import work.Info.element.event;
import work.Info.element.date;
import work.Info.element.note;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static work.Info.utility.getToday;

/**
 * Created by ting huang on 11/2/2016.
 */
public class course {
    private String name;
    private date registered;
    private int credit;
    private note note =  new note("Empty");
    private ArrayList<event> events;

    /**
     * course will have credit information, name of the course,               #note week 1
     * event will be exam day or other important message
     * registered, will the date that you got the course
     * note is just something that you wanna tell your self.
     */

    /**
     * there is not much of change for this file, but it comes clear          # note week2
     * that, this file need to use utility function in order to give yourself
     * a easy time.
     */
    public course(int credit, String name, ArrayList<event> events, String note){
        this.credit = credit;
        this.name = name;
        if(events!=null){
            this.events = events;
        }
        this.registered = getToday();
        if(note!=null){
            this.note = new note(note);
        }
    }
    public course(int credit, String name, date registered,ArrayList<event> events, String note){
        this.credit = credit;
        this.name = name;
        if(events!=null){
            this.events = events;
        }
        this.registered = registered;
        if(note!=null){
            this.note = new note(note);
        }
    }
    public int getCredit(){
        return credit;
    }
    public String getName(){
        return name;
    }
    public ArrayList<event> getEvents(){
        return events;
    }
    public date getRegistered(){
        return registered;
    }
    public note getNote(){
        return note;
    }
    public void setEvents(ArrayList<event> events){
        this.events = events;
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof course) {
            course copy = (course) o;
            //checking event first
            ArrayList<event> tEvent = copy.getEvents();
            for(event temp: tEvent){
                if(! this.events.contains(temp)){
                    return false;
                }
            }
            return this.credit == copy.getCredit() && this.name.equals(copy.getName())
                    && this.registered.equals(copy.getRegistered())&& this.note.equals(copy.getNote());
        } else {
            return false;
        }
    }
}
