package com.example.yutong.finalproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yutong on 11/9/16.
 */

public class EventDbHelper extends SQLiteOpenHelper {
    public EventDbHelper(Context context) {
        super(context, EventContract.DB_NAME, null, EventContract.DB_VERSION);
    }

    /**
     * query data
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + EventContract.EventEntry.TABLE + " ( " +
                EventContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventContract.EventEntry.COL_EVENT_NAME + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_EVENT_DATE + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_EVENT_INFO + " TEXT NOT NULL, " +
                EventContract.EventEntry.COL_EVENT_PRIO + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE);
        onCreate(db);
    }
}
