package com.example.yutong.finalproject;

import com.example.yutong.finalproject.db.ExpContract;
import com.example.yutong.finalproject.db.ExpDbHelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    private ExpDbHelper expDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        expDbHelper = new ExpDbHelper(this);

        Button eventButton = (Button) findViewById(R.id.Event);
        eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, eventActivity.class));
            }
        });

        Button courseButton = (Button) findViewById(R.id.Course);
        courseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, courseActivity.class));
            }
        });

        Button NoteButton = (Button) findViewById(R.id.Note);
        NoteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, noteActivity.class));
            }
        });

        TextView ExpText = (TextView) findViewById(R.id.exp);
        String exp = getExp();
        ExpText.setText("Level: " + Integer.parseInt(exp)/5 + "  Exp: " + exp);
        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
                Toast.makeText(getApplicationContext(), month + "/" + dayOfMonth + "/" + year,
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, viewActivity.class);
                String date = month + "/" + dayOfMonth + "/" + year;
                intent.putExtra(Intent.EXTRA_TEXT, date);
                startActivity(intent);
            }
        });
    }

    public String getExp(){
        SQLiteDatabase db = expDbHelper.getReadableDatabase();

        /*Cursor cursor = db.query(ExpContract.ExpEntry.TABLE,
                new String[]{ExpContract.ExpEntry._ID, ExpContract.ExpEntry.COL_EXP},
                null, null, null, null, null);
                */
        Cursor cursor = db.rawQuery("SELECT * FROM " + ExpContract.ExpEntry.TABLE, null);
            int idx = cursor.getColumnIndex(ExpContract.ExpEntry.COL_EXP);
            String ret = "10";
        DatabaseUtils.dumpCursor(cursor);
            if(cursor.moveToFirst()) {
                //Log.d("cursor:", "null");
                ret = cursor.getString(idx);
            }
            cursor.close();
            db.close();

            return ret;
    }
}
