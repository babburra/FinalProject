package work.Info.element;

/**
 * Created by ting huang on 11/2/2016.
 */
public class note {
    private String note;

    /**
     * This class is little bit redundant to have                         #note week1
     * since note got nothing but String class
     * but with the right name, we will know that String
     * is a note!
     */
    /**
     *  it had slowly become clear that the structure is much            # note week2
     *  use-less then usual, more code wring to do something
     *  just a string is doing
     */
    public note(String note){
        this.note = note;
    }
    public String getNote(){
        return this.note;
    }
}
