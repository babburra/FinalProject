package work.Info.element;

import static work.Info.element.prio.same;

/**
 * Created by ting huang on 11/2/2016.
 */
public class event {
    private date date;
    private String name;
    private String info;
    private prio prio;

    /**
     * setting up the event                                       #note week1
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
     *  was trying to override this function but due to override already happen in     #note week1
     *  supper class, plane got scratched
     */
    public boolean compareTo(event temp){
        return this.date.equals(temp.getDate()) && this.name.equals(temp.getName())
                && this.info.equals(temp.getInfo()) && (same(temp.getPrio(),this.prio)==0);
    }
}
