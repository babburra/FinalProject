package com.example.yutong.finalproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yutong on 11/17/16.
 */

public class CourseDbHelper extends SQLiteOpenHelper {
    public CourseDbHelper(Context context) {
        super(context, CourseContract.DB_NAME, null, CourseContract.DB_VERSION);
    }

    /**
     * query data
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CourseContract.CourseEntry.TABLE_EVENT + " ( " +
                CourseContract.CourseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CourseContract.CourseEntry.COL_EVENT_BELONGS + "TEXT NOT NULL, " +
                CourseContract.CourseEntry.COL_EVENT_NAME + " TEXT NOT NULL, " +
                CourseContract.CourseEntry.COL_EVENT_DATE + " DATE, " +
                CourseContract.CourseEntry.COL_EVENT_INFO + " TEXT NOT NULL, " +
                CourseContract.CourseEntry.COL_EVENT_PRIO + " TEXT NOT NULL);";

        String createTable2 = "CREATE TABLE " + CourseContract.CourseEntry.TABLE_COURSE + " ( " +
                CourseContract.CourseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CourseContract.CourseEntry.COL_COURSE_NAME + "TEXT NOT NULL, " +
                CourseContract.CourseEntry.COL_COURSE_CREDIT + " INT, " +
                CourseContract.CourseEntry.COL_COURSE_NOTE + " TEXT);";
        db.execSQL(createTable);
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CourseContract.CourseEntry.TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + CourseContract.CourseEntry.TABLE_EVENT);
        onCreate(db);
    }
}
