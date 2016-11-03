package work.Info;
import work.Info.element.event;
import work.Info.element.date;
import work.Info.element.note;

import static work.Info.utility.getToday;

/**
 * Created by ting huang on 11/2/2016.
 */
public class course {
    private int credit;
    private String name;
    private event[] events = null;
    private date registered;
    private note note =  new note("Empty");

    /**
     * course will have credit information, name of the course,
     * event will be exam day or other important message
     * registered, will the date that you got the course
     * note is just something that you wanna tell your self.
     */
    public course(int credit, String name, event[] events, String note){
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
    public int getCredit(){
        return credit;
    }
    public String getName(){
        return name;
    }
    public event[] getEvents(){
        return events;
    }
    public date getRegistered(){
        return registered;
    }
    public note getNote(){
        return note;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof course) {
            course temp = (course) o;
            //checking event first
            event[] tEvent = temp.getEvents();
            if(temp.getEvents().length != this.events.length){
                return false;
            }
            for(int i=0; i<this.events.length;i++){
                if(!(this.events[i].compareTo(tEvent[i]))){
                    return false;
                }
            }
            return this.credit == temp.getCredit() && this.name.equals(temp.getName())
                    && this.registered.equals(temp.getRegistered())&& this.note.equals(temp.getNote());
        } else {
            return false;
        }
    }
}
