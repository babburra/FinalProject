package com.example.yutong.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yutong.finalproject.db.EventContract;
import com.example.yutong.finalproject.db.EventDbHelper;
import com.example.yutong.finalproject.db.NoteContract;

import java.util.ArrayList;

/**
 * Created by yutong on 11/3/16.
 */
public class eventActivity extends AppCompatActivity{
    private EventDbHelper eventHelper;
    private ListView eventListView;
    private ArrayAdapter<String> eventAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        // init noteActivity helper when oncreate
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

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Add a new event");
                final EditText nameEditText = new EditText(this);
                nameEditText.setHint("Name");
                layout.addView(nameEditText);
                final EditText dateEditText = new EditText(this);
                dateEditText.setHint("Date");
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
                        //date format in database
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
                dialog.create();
                dialog.show();
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
        Cursor cursor = db.query(EventContract.EventEntry.TABLE,
                new String[]{EventContract.EventEntry._ID, EventContract.EventEntry.COL_EVENT_NAME,
                        EventContract.EventEntry.COL_EVENT_DATE, EventContract.EventEntry.COL_EVENT_INFO,
                        EventContract.EventEntry.COL_EVENT_PRIO},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(EventContract.EventEntry.COL_EVENT_NAME);
            eventList.add(cursor.getString(idx));
        }

        if (eventAdapter == null) {
            eventAdapter = new ArrayAdapter<>(this,
                    R.layout.activity_note_todo,
                    R.id.note_title,
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
    public void deleteNote(View view) {
        View parent = (View) view.getParent();
        TextView eventTextView = (TextView) parent.findViewById(R.id.note_title);
        String note = String.valueOf(eventTextView.getText());
        SQLiteDatabase db = eventHelper.getWritableDatabase();
        db.delete(EventContract.EventEntry.TABLE,
                EventContract.EventEntry.COL_EVENT_NAME + " = ?",
                new String[]{note});
        db.close();
        updateUI();
    }
}
