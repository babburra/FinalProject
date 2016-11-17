package com.example.yutong.finalproject.db;

import android.provider.BaseColumns;

/**
 * Created by yutong on 11/9/16.
 */

public class CourseContract {
    public static final String DB_NAME = "com.example.yutong.finalproject.course.db;";
    public static final int DB_VERSION = 1;

    public class CourseEntry implements BaseColumns {
        public static final String TABLE_COURSE = "course";

        public static final String COL_COURSE_NAME = "name";
        public static final int COL_COURSE_CREDIT = 0;
        public static final String COL_COURSE_NOTE = "note";

        public static final String TABLE_EVENT = "event";
        public static final String COL_EVENT_BELONGS = "course";
        public static final String COL_EVENT_NAME = "name";
        public static final String COL_EVENT_DATE = "date";
        public static final String COL_EVENT_INFO = "info";
        public static final String COL_EVENT_PRIO = "prio";

    }
}
