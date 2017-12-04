package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.habittracker.data.HabitsDBHelper;
import com.example.android.habittracker.data.HabitsContract.HabitsEntry;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class HabitsActivity extends AppCompatActivity {

    public static final String LOG_TAG = HabitsActivity.class.getSimpleName();

    private HabitsDBHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker);

        mDbHelper = new HabitsDBHelper(this);

        insertHabits();

        getHabits();
    }

    private void insertHabits() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp = Timestamp.valueOf("2017-11-27 07:00:00.0");
        String dateTime = simpleDateFormat.format(timestamp);

        ContentValues values = new ContentValues();
        values.put(HabitsEntry.COLUMN_HABIT_DESCRIPTION, "Deixar meu filho na escola");
        values.put(HabitsEntry.COLUMN_HABIT_DATE_TIME, dateTime);

        long newRowId = db.insert(HabitsEntry.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Log.i(LOG_TAG, "Inserindo - ID: " + String.valueOf(newRowId));
        } else {
            Log.i(LOG_TAG, "Erro ao inserir no banco de dados");
        }
    }

    private void getHabits() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitsEntry._ID,
                HabitsEntry.COLUMN_HABIT_DESCRIPTION,
                HabitsEntry.COLUMN_HABIT_DATE_TIME
        };

        Cursor cursor = db.query(HabitsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitsEntry._ID);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_DESCRIPTION);
            int dateTimeColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_DATE_TIME);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex);
                String currentDateTime = cursor.getString(dateTimeColumnIndex);

                Log.i(LOG_TAG, "Lendo - ID: " + String.valueOf(currentID) +
                        " - Description: " + currentDescription + " - DateTime: " + currentDateTime);
            }
        } finally {
            cursor.close();
        }
    }
}
