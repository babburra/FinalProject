package com.example.yutong.finalproject.db;

import android.provider.BaseColumns;

/**
 * Created by yutong on 11/30/16.
 */

public class ExpContract {
    public static final String DB_NAME = "com.example.yutong.finalproject.exp.db;";
    public static final int DB_VERSION = 3;

    public class ExpEntry implements BaseColumns {
        public static final String TABLE = "EXP_TABLE";

        public static final String COL_EXP = "EXP_COLUMN";
    }
}
