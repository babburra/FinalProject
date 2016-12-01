package com.example.yutong.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yutong.finalproject.db.CourseContract;
import com.example.yutong.finalproject.db.CourseDbHelper;
import com.example.yutong.finalproject.db.EventContract;
import com.example.yutong.finalproject.structure.course;
import com.example.yutong.finalproject.structure.unit;

/**
 * Created by yutong on 11/2/16.
 */
public class addCourse extends AppCompatActivity{
    private CourseDbHelper courseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
        
        courseHelper = new CourseDbHelper(this);

        Button courseButton = (Button) findViewById(R.id.add_course_event);
        courseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(addCourse.this, addEvent.class));
                LinearLayout layout = new LinearLayout(addCourse.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                AlertDialog.Builder dialog = new AlertDialog.Builder(addCourse.this);
                dialog.setTitle("Add a new event");
                final EditText nameEditText = new EditText(addCourse.this);
                nameEditText.setHint("Name");
                layout.addView(nameEditText);
                final EditText dateEditText = new EditText(addCourse.this);
                dateEditText.setHint("YYYY-MM-DD HH:MM");
                layout.addView(dateEditText);
                final EditText infoEditText = new EditText(addCourse.this);
                infoEditText.setHint("Info");
                layout.addView(infoEditText);
                final EditText prioEditText = new EditText(addCourse.this);
                prioEditText.setHint("Priority");
                layout.addView(prioEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = String.valueOf(nameEditText.getText());
                        //date format in database
                        String newDate = String.valueOf(dateEditText.getText());
                        String newInfo = String.valueOf(infoEditText.getText());
                        String newPrio = String.valueOf(prioEditText.getText());
                        SQLiteDatabase db = courseHelper.getWritableDatabase();
                        ContentValues value = new ContentValues();
                        value.put(CourseContract.CourseEntry.COL_EVENT_NAME, newName);
                        value.put(CourseContract.CourseEntry.COL_EVENT_DATE, newDate);
                        value.put(CourseContract.CourseEntry.COL_EVENT_INFO, newInfo);
                        value.put(CourseContract.CourseEntry.COL_EVENT_PRIO, newPrio);
                        db.insertWithOnConflict(CourseContract.CourseEntry.TABLE_EVENT,
                                null,
                                value,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        //update ui after adding a new noteActivity
                        //updateUI();
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.setView(layout);
                dialog.create();
                dialog.show();
            }
        });
    }

    /**
     * render menu bar with add icon for noteActivity page
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_ic, menu);

        MenuItem item = menu.findItem(R.id.action_save);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //YourActivity.this.someFunctionInYourActivity();
                EditText newText = (EditText) findViewById(R.id.course_name);
                String newCourseName = newText.getText().toString();

                newText = (EditText) findViewById(R.id.course_credit);
                int newCredit = Integer.parseInt(newText.getText().toString());


                // need to insert events

                newText = (EditText) findViewById(R.id.course_note);
                String newNote = newText.getText().toString();

                SQLiteDatabase db = courseHelper.getWritableDatabase();
                ContentValues value = new ContentValues();
                value.put(CourseContract.CourseEntry.COL_COURSE_NAME, newCourseName);
                value.put(CourseContract.CourseEntry.COL_COURSE_CREDIT, newCredit);
                value.put(CourseContract.CourseEntry.COL_COURSE_NOTE, newNote);
                db.insertWithOnConflict(CourseContract.CourseEntry.TABLE_COURSE,
                        null,
                        value,
                        SQLiteDatabase.CONFLICT_REPLACE);
                db.close();

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
