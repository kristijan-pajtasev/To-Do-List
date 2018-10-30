package com.example.kristijan.assigment01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Helper class for interaction with SQLite database.
 * @author Kristijan Pajtasev
 */
public class DBHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE = "create table tasks(" +
            "id integer primary key autoincrement, " +
            "task string," +
            "completed integer default 0," +
            "created datetime default current_timestamp" +
            ")";
    final String DROP_TABLE = "drop table tasks";

    /**
     * @param context
     * @param name database name
     * @param factory
     * @param version
     */
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

    /**
     * @param sqLiteDatabase
     * @return all uncompleted tasks in format <code>ArrayList<Task></code>
     */
    public ArrayList<Task> getTasks(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tasks where completed='0' order by created DESC",null);

        ArrayList<Task> tasks = new ArrayList<Task>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            tasks.add(getTaskFromCurrentCursor(cursor));
        }

        return tasks;
    }

    /**
     * @param sqLiteDatabase
     * @return completed tasks in format <code>ArrayList<Task></code>
     */
    public ArrayList<Task> getCompletedTasks(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tasks where completed='1' order by created DESC",null);

        ArrayList<Task> tasks = new ArrayList<Task>();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            tasks.add(getTaskFromCurrentCursor(cursor));
        }

        return tasks;
    }

    /**
     * @param sqLiteDatabase
     * @param task
     */
    public void createTask(SQLiteDatabase sqLiteDatabase, String task, int isCompleted) {
        ContentValues cv = new ContentValues();
        cv.put("task", task);
        cv.put("completed", isCompleted);
        sqLiteDatabase.insert("tasks", null, cv);
    }

    /**
     * @param sqLiteDatabase
     * @param id of task
     * @return Task with given id
     */
    public Task getTask(SQLiteDatabase sqLiteDatabase, String id) {
        Cursor cursor = sqLiteDatabase.rawQuery("select * from tasks where id=" + id,null);
        cursor.moveToFirst();
        return getTaskFromCurrentCursor(cursor);
    }

    /**
     * @param sqLiteDatabase
     * @param id
     * @param task
     * Updates Task with given id to task value
     */
    public void updateTask(SQLiteDatabase sqLiteDatabase, String id, String task, int completed) {
        ContentValues cv = new ContentValues();
        cv.put("task", task);
        cv.put("completed", completed);
        sqLiteDatabase.update("tasks", cv, "id="+ id, null);
    }

    /**
     * @param sqLiteDatabase
     * @param id
     * Deletes task from database with given id.
     */
    public void deleteItem(SQLiteDatabase sqLiteDatabase, int id) {
        sqLiteDatabase.delete("tasks", "id="+ id, null);
    }

    /**
     * @param cursor
     * @return generated Task instance from current cursor
     */
    private Task getTaskFromCurrentCursor(Cursor cursor) {
        return new Task(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2) == 1);
    }
}