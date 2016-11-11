package work.Info.element;

/**
 * Created by ting huang on 11/2/2016.
 */
public class date {
    private int month;
    private int day;
    private int year;

    /**
     * most of the code will give to utility to be parse into string          #note week 2
     * format, some of the usage might seem un-need, but we will see later
     * on.
     */
    public date(int month, int day, int year){
        changeDay(day);
        changeMonth(month);
        changeYear(year);
    }
    private void changeDay(int day){
        this.day = day;
    }
    private void changeMonth(int month){
        this.month = month;
    }
    private void changeYear(int year){
        this.year = year;
    }
    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof date) {
            date temp = (date) o;
            return this.month == temp.getMonth() && this.day == temp.getDay() && this.year == temp.getYear();
        } else {
            return false;
        }
    }
}
