package com.example.yutong.finalproject.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by yutong on 11/9/16.
 */

public class NoteDbHelper extends SQLiteOpenHelper{
    public NoteDbHelper(Context context) {
        super(context, NoteContract.DB_NAME, null, NoteContract.DB_VERSION);
    }

    /**
     * query data
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + NoteContract.NoteEntry.TABLE + " ( " +
                NoteContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NoteContract.NoteEntry.COL_NOTE_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteContract.NoteEntry.TABLE);
        onCreate(db);
    }
}
