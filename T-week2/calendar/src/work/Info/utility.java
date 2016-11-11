package work.Info;

import work.Info.element.date;
import work.Info.element.event;
import work.Info.element.note;
import work.Info.element.prio;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ting huang on 11/3/2016.
 */
public class utility {

    /**
     * getting today's day to set-up the calendar interface                  #note week1
     * @return date of today
     */
    public static date getToday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        return (new date(month,day,year));
    }

    public static void printunit(unit unit){
        printCourse(unit.getCourses());
        printGlobalEvent(unit.getEvents());
        printNote(unit.getNotes());
    }
    public static void printCourse(ArrayList<course> courses){
        for(course temp: courses){
            printCourse(temp);
            System.out.println("-----------");
        }
    }
    public static void printCourse(course course){
        System.out.println("name:" + course.getName());
        System.out.println("credit:" + course.getCredit());
        System.out.println("date:" + dateToString(course.getRegistered()));
        System.out.println("note:" + course.getNote().getNote());
        printEvent(course.getEvents());
    }
    public static void printGlobalEvent(ArrayList<event> events){
        int ID = 1;
        for(event temp:events){
            printEvent(temp, ID);
            ID++;
        }
        System.out.println("-----------");
    }
    public static void printEvent(ArrayList<event> events){
        int ID = 1;
        for(event temp: events){
            printEvent(temp, ID);
            ID++;
        }
    }
    public static void printEvent(event events, int id){
        System.out.print("ID:"+id +" " + events.getName()+" ");
        System.out.print(dateToString(events.getDate())+ " ");
        System.out.println(events.getPrio().toString());
        System.out.println("Describe:" + events.getInfo());
    }
    public static void printNote(ArrayList<note> notes){
        int ID = 1;
        for(note note: notes){
            printNote(note, ID);
            ID++;
        }
    }
    public static void printNote(note note, int id){
        System.out.println("ID:"+ id+" "+note.getNote());
    }
    public static String dateToString(date date){
        String temp = date.getMonth() + "-"+date.getDay()+"-"+date.getYear();
        return temp;
    }
    public static date stringToDate(String date){
        String[] temp = date.split("-");
        return new date(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
                Integer.parseInt(temp[2]));
    }
    public static event stringToEvent(String event){
        String[] item = event.split("\\|");
        date date = stringToDate(item[0]);
        prio hi = prio.valueOf(item[3]);
        return new event(date,item[1],item[2],hi);
    }
    public static String eventToString(event event){
        return dateToString(event.getDate())+"|"+event.getName()+"|"+
                event.getInfo()+"|"+event.getPrio().toString();
    }
}
