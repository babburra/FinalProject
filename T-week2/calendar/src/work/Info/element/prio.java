package work.Info.element;

/**
 * Created by ting huang on 11/2/2016.
 */
public enum prio {
    /**
     * the priority of the task                                                #note week1
     */
    low,
    median,
    high;

    public static int same(prio first, prio second){
        if(first == second){
            return 0;
        }else if(first==high ){
            return 1;
        }else if(second == high){
            return 2;
        }else if(first == median){
            return 1;
        }else{
            return 2;
        }
    }
}
