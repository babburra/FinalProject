package com.example.yutong.finalproject;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yutong.finalproject.db.EventContract;
import com.example.yutong.finalproject.db.EventDbHelper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yutong on 11/30/16.
 */

public class viewActivity extends AppCompatActivity {
    private EventDbHelper eventHelper;
    private ListView eventListView;
    private ArrayAdapter<String> eventAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventHelper = new EventDbHelper(this);
        eventListView = (ListView) findViewById(R.id.Event);
        //initially shows data
        updateUI();
    }

    /**
     * render menu bar with add icon for noteActivity page
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_ic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Add a new event");
                final EditText nameEditText = new EditText(this);
                nameEditText.setHint("Name");
                layout.addView(nameEditText);
                final EditText dateEditText = new EditText(this);
                dateEditText.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
                layout.addView(dateEditText);
                final EditText infoEditText = new EditText(this);
                infoEditText.setHint("Info");
                layout.addView(infoEditText);
                final EditText prioEditText = new EditText(this);
                prioEditText.setHint("Priority");
                layout.addView(prioEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = String.valueOf(nameEditText.getText());
                        String newDate = String.valueOf(dateEditText.getText());
                        String newInfo = String.valueOf(infoEditText.getText());
                        String newPrio = String.valueOf(prioEditText.getText());
                        SQLiteDatabase db = eventHelper.getWritableDatabase();
                        ContentValues value = new ContentValues();
                        value.put(EventContract.EventEntry.COL_EVENT_NAME, newName);
                        value.put(EventContract.EventEntry.COL_EVENT_DATE, newDate);
                        value.put(EventContract.EventEntry.COL_EVENT_INFO, newInfo);
                        value.put(EventContract.EventEntry.COL_EVENT_PRIO, newPrio);
                        db.insertWithOnConflict(EventContract.EventEntry.TABLE,
                                null,
                                value,
                                SQLiteDatabase.CONFLICT_REPLACE);
                        db.close();
                        //update ui after adding a new noteActivity
                        updateUI();
                    }
                });

                dialog.setNegativeButton("Cancel", null);
                dialog.setView(layout);
                final AlertDialog d = dialog.create();
                d.show();
                dateEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                        if(isValid(dateEditText.getText().toString()))
                            b.setEnabled(true);
                        else
                            b.setEnabled(false);
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * update user interface after database has changed
     */
    private void updateUI() {
        ArrayList<String> eventList = new ArrayList<>();
        SQLiteDatabase db = eventHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ EventContract.EventEntry.TABLE + " WHERE "
                                + EventContract.EventEntry.COL_EVENT_DATE + " = ?",
                new String[]{getIntent().getStringExtra(Intent.EXTRA_TEXT)});
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_NAME);
            eventList.add(cursor.getString(idx));
        }

        if (eventAdapter == null) {
            eventAdapter = new ArrayAdapter<>(this,
                    R.layout.activity_event_todo,
                    R.id.event_title,
                    eventList);
            eventListView.setAdapter(eventAdapter);
        } else {
            eventAdapter.clear();
            eventAdapter.addAll(eventList);
            eventAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }


    /**
     * delete a noteActivity after it's done, update ui
     * @param view
     */
    public void deleteEvent(View view) {
        View parent = (View) view.getParent();
        TextView eventTextView = (TextView) parent.findViewById(R.id.event_title);
        String event = String.valueOf(eventTextView.getText());
        SQLiteDatabase db = eventHelper.getWritableDatabase();
        db.delete(EventContract.EventEntry.TABLE,
                EventContract.EventEntry.COL_EVENT_NAME + " = ?",
                new String[]{event});
        db.close();
        updateUI();
    }

    public void viewEvent(View view){
        View parent = (View) view.getParent();
        TextView eventTextView = (TextView) parent.findViewById(R.id.event_title);
        String event = String.valueOf(eventTextView.getText());
        SQLiteDatabase db = eventHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + EventContract.EventEntry.TABLE + " WHERE "
                + EventContract.EventEntry.COL_EVENT_NAME + "=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[] {event});
        String name = null, date = null, info = null, prio = null;
        if(cursor.moveToFirst()){
            int n = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_NAME);
            name = cursor.getString(n);
            cursor.moveToNext();
        }
        if(cursor.moveToFirst()) {
            int temp = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_DATE);
            date = cursor.getString(temp);
            cursor.moveToNext();
        }
        if(cursor.moveToFirst()) {
            int temp = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_INFO);
            info = cursor.getString(temp);
            cursor.moveToNext();
        }
        if(cursor.moveToFirst()) {
            int temp = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_PRIO);
            prio = cursor.getString(temp);
            cursor.moveToNext();
        }

        //int date = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_DATE);
        //int info = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_INFO);
        //int prio = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_PRIO);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Name: " + name + "\n"
                + "Date: " + date + "\n"
                + "Info: " + info + "\n"
                + "Priority: " + prio + "\n")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public boolean isValid(String date){
        if(date.length()!=10)
            return false;
        int month = Integer.parseInt(date.substring(0,2),10);
        int day = Integer.parseInt(date.substring(3,5),10);
        int year = Integer.parseInt(date.substring(6,10));
        if(year >= 1999 && year <= 2040 && month < 13 && month > 0){
            if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
                if(day<32&&day>0)
                    return true;
            }
            if(month==4||month==6||month==9||month==11){
                if(day<31&&day>0)
                    return true;
            }
            if(month==2){
                if(year%4==0&&day<30&&day>0)
                    return true;
                if(day<29&&day>0)
                    return true;
            }
        }
        return false;
    }
}
