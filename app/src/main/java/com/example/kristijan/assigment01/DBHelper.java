package com.example.kristijan.assigment01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public ArrayList<ToDo> getTasks(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tasks",null);

        ArrayList<ToDo> tasks = new ArrayList<ToDo>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            tasks.add(new ToDo(cursor.getInt(0),cursor.getString(1)));
        }

        return tasks;
    }

}
