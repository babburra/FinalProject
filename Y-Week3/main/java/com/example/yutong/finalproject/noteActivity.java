package com.example.yutong.finalproject;

import android.content.ContentValues;
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
import android.widget.ListView;
import android.widget.TextView;

import com.example.yutong.finalproject.db.NoteContract;
import com.example.yutong.finalproject.db.NoteDbHelper;
import com.example.yutong.finalproject.structure.element.note;

import java.util.ArrayList;

import static com.example.yutong.finalproject.structure.save.*;
import static com.example.yutong.finalproject.structure.load.*;

/**
 * Created by yutong on 11/9/16.
 */

public class noteActivity extends AppCompatActivity {
    private NoteDbHelper noteHelper;
    private ListView noteListView;
    private ArrayAdapter<String> noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteHelper = new NoteDbHelper(this);
        noteListView = (ListView) findViewById(R.id.Note);
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

    /**
     * AlertDialog for user input after clicking add icon
     * store data into database then update ui
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                //Log.d(TAG, "Add a new noteActivity");
                final EditText noteEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new note")
                        .setView(noteEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String str = String.valueOf(noteEditText.getText());
                                ArrayList<note> temp = new ArrayList<>();
                                temp.add(new note("hello"));
                                saveFileNotes(temp);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * update user interface after database has changed
     * abstract class
     */
    private void updateUI() {
        ArrayList<String> noteList = new ArrayList<>();
        SQLiteDatabase db = noteHelper.getReadableDatabase();
        Cursor cursor = db.query(NoteContract.NoteEntry.TABLE,
                new String[]{NoteContract.NoteEntry._ID, NoteContract.NoteEntry.COL_NOTE_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(NoteContract.NoteEntry.COL_NOTE_TITLE);
            noteList.add(cursor.getString(idx));
        }

        if (noteAdapter == null) {
            noteAdapter = new ArrayAdapter<>(this,
                    R.layout.activity_note_todo,
                    R.id.note_title,
                    noteList);
            noteListView.setAdapter(noteAdapter);
        } else {
            noteAdapter.clear();
            noteAdapter.addAll(noteList);
            noteAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    /**
     * delete a noteActivity after it's done, update ui
     * @param view
     * abstract class
     */
    public void deleteNote(View view) {
        View parent = (View) view.getParent();
        TextView noteTextView = (TextView) parent.findViewById(R.id.note_title);
        String note = String.valueOf(noteTextView.getText());
        SQLiteDatabase db = noteHelper.getWritableDatabase();
        db.delete(NoteContract.NoteEntry.TABLE,
                NoteContract.NoteEntry.COL_NOTE_TITLE + " = ?",
                new String[]{note});
        db.close();
        updateUI();
    }
}
