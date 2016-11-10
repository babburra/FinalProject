package com.example.yutong.finalproject.db;
import android.provider.BaseColumns;

/**
 * Created by yutong on 11/9/16.
 */

/**
 * Database fore notes
 * defines constants which used to access data
 */
public class NoteContract {
    public static final String DB_NAME = "com.example.yutong.finalproject.note.db;";
    public static final int DB_VERSION = 1;

    public class NoteEntry implements BaseColumns {
        public static final String TABLE = "notes";

        public static final String COL_NOTE_TITLE = "title";
    }
}
