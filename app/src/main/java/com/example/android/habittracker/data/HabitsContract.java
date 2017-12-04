package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by marcelo on 03/12/17.
 */

public class HabitsContract {

    private HabitsContract() {
    }

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitsEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the pet (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Description of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DESCRIPTION = "description";

        /**
         * Date and Time of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_DATE_TIME = "datetime";
    }
}
