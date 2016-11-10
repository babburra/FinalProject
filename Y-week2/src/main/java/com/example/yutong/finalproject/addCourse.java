package com.example.yutong.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.yutong.finalproject.structure.course;

/**
 * Created by yutong on 11/2/16.
 */
public class addCourse extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcourse);
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
                String strCredit = newText.getText().toString();
                int newCredit = Integer.valueOf(strCredit);

                // need to insert events

                newText = (EditText) findViewById(R.id.course_note);
                String newNote = newText.getText().toString();

                course newCourse = new course(newCredit, newCourseName, null, newNote);
                Log.d("course", newCourse.getName());
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
