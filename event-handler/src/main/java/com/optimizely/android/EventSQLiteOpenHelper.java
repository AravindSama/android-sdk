package com.optimizely.android;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import org.slf4j.Logger;

/**
 * Created by jdeffibaugh on 7/21/16 for Optimizely.
 *
 * Handles transactions for the events db
 */
public class EventSQLiteOpenHelper extends SQLiteOpenHelper {

    static final int VERSION = 1;
    static final String DB_NAME = "optly-events";

    static final String SQL_CREATE_EVENT_TABLE =
            "CREATE TABLE " + EventTable.NAME + " (" +
                    EventTable._ID + " INTEGER PRIMARY KEY, " +
                    EventTable.Column.URL + " TEXT NOT NULL" +
            ")";

    private static final String SQL_DELETE_EVENT_TABLE =
            "DROP TABLE IF EXISTS " + EventTable.NAME;

    private final Logger logger;

    public EventSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Logger logger) {
        super(context, name, factory, version);
        this.logger = logger;
    }

    public EventSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, Logger logger) {
        super(context, name, factory, version, errorHandler);
        this.logger = logger;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EVENT_TABLE);
        logger.info("Created event table with SQL: {}", SQL_CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Create an upgrade strategy once we actually upgrade the schema.
    }
}
