package com.example.yutong.finalproject.db;

import android.provider.BaseColumns;

/**
 * Created by yutong on 11/9/16.
 */

public class CourseContract {
    public static final String DB_NAME = "com.example.yutong.finalproject.db;";
    public static final int DB_VERSION = 1;

    public class CourseEntry implements BaseColumns {
        public static final String TABLE = "course";

        public static final String COL_COURSE_NAME = "name";
        public static final int COL_COURSE_CREDIT = 0;
        public static final String COL_COURSE_NOTE = "note";
    }
}
