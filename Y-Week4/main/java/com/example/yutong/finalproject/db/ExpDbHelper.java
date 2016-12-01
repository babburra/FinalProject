package com.example.yutong.finalproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yutong on 11/30/16.
 */

public class ExpDbHelper extends SQLiteOpenHelper {
    public ExpDbHelper(Context context){
        super(context, ExpContract.DB_NAME, null, ExpContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ExpContract.ExpEntry.TABLE + " ( " +
                ExpContract.ExpEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExpContract.ExpEntry.COL_EXP + " TEXT NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ExpContract.ExpEntry.TABLE);
        onCreate(db);
    }
}
