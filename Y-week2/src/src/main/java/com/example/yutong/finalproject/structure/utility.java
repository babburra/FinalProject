package com.example.yutong.finalproject.structure;

import com.example.yutong.finalproject.structure.element.*;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ting huang on 11/3/2016.
 */
public class utility {

    /**
     * getting today's day to set-up the calendar interface                  #noteActivity week1
     * @return date of today
     */
    public static date getToday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        return (new date(month,day,year));
    }
    public static void printCourse(course course){
        System.out.println("name:" + course.getName());
        System.out.println("credit:" + course.getCredit());
        System.out.println("date:" + dateToString(course.getRegistered()));
        System.out.println("noteActivity:" + course.getNote());
        printEvent(course.getEvents());
    }
    public static void printEvent(ArrayList<event> events){
        for(event temp: events){
            System.out.println("event date:" + dateToString(temp.getDate()));
            System.out.println("event name:" + temp.getName());
            System.out.println("event info:" + temp.getInfo());
            System.out.println("event prio:" + temp.getPrio().toString());
        }
    }
    //TODO required test
    public static String dateToString(date date){
        String temp = date.getMonth() + "-"+date.getDay()+"-"+date.getYear();
        return temp;
    }
    //TODO required test
    public static date stringToDate(String date){
        String[] temp = date.split("-");
        return new date(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),
                Integer.parseInt(temp[2]));
    }
    //TODO required test
    public static event stringToEvent(String event){
        String[] item = event.split("|");
        date date = stringToDate(item[0]);
        prio hi = prio.valueOf(item[3]);
        return new event(date,item[1],item[2],hi);
    }
    //TODO required test
    public static String eventToString(event event){
        return dateToString(event.getDate())+"|"+event.getName()+"|"+
                event.getInfo()+"|"+event.getPrio().toString();
    }
}