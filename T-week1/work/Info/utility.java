package work.Info;

import work.Info.element.date;

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
}
