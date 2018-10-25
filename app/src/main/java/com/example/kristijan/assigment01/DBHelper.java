package com.example.kristijan.assigment01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE = "create table tasks(" +
            "id integer primary key autoincrement, " +
            "description string" +
            ")";
    final String DROP_TABLE = "drop table tasks";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        ContentValues cv = new ContentValues();
        cv.put("description", "some desc");
        db.insert("tasks", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

}
