package com.example.yutong.finalproject.db;

import android.provider.BaseColumns;

/**
 * Created by yutong on 11/9/16.
 */

public class EventContract {
    public static final String DB_NAME = "com.example.yutong.finalproject.event.db;";
    public static final int DB_VERSION = 1;

    public class EventEntry implements BaseColumns {
        public static final String TABLE = "events";

        public static final String COL_EVENT_NAME = "name";
        public static final String COL_EVENT_DATE = "date";
        public static final String COL_EVENT_INFO = "info";
        public static final String COL_EVENT_PRIO = "prio";
    }
}
