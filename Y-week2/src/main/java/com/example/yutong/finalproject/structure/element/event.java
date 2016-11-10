package com.example.yutong.finalproject.structure.element;

/**
 * Created by ting huang on 11/9/16.
 */

public class event {
    private date date;
    private String name;
    private String info;
    private prio prio;

    /**
     * setting up the event                                       #noteActivity week1
     */
    public event(date date, String name, String info,prio prio){
        ChangeDate(date);
        ChangeInfo(info);
        ChangeName(name);
        ChangePrio(prio);
    }
    private void ChangeDate(date date){
        this.date = date;
    }
    private void ChangeName(String name){
        this.name = name;
    }
    private void ChangeInfo(String info){
        this.info = info;
    }
    private void ChangePrio(prio prio){
        this.prio = prio;
    }
    public date getDate(){
        return this.date;
    }
    public String getName(){
        return this.name;
    }
    public String getInfo(){
        return this.info;
    }
    public prio getPrio(){
        return this.prio;
    }

    /**
     *  was trying to override this function but due to override already happen in     #noteActivity week1
     *  supper class, plane got scratched
     */
    public boolean compareTo(event temp){
        return this.date.equals(temp.getDate()) && this.name.equals(temp.getName())
                && this.info.equals(temp.getInfo()) && (com.example.yutong.finalproject.structure.element.prio.same(temp.getPrio(),this.prio)==0);
    }
}
