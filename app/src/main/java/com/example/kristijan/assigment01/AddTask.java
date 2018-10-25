package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddTask extends Activity {
    private EditText taskContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("ADD ACTIVITY: ", "AddTask activity started");

        final DBHelper dbHelper = new DBHelper(this, "tasks.db", null, 1);
        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();


//        ContentValues cv = new ContentValues();
//        cv.put("description", "some desc");
//        sqLiteDatabase.insert("tasks", null, cv);

        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        Button saveButton = (Button)findViewById(R.id.saveButton);
        taskContent = (EditText) findViewById(R.id.taskContent);

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ADD ACTIVITY: ", "Cancel adding task");
                finish();
            }
        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ADD ACTIVITY: ", "Save new task");
                String content = taskContent.getText().toString();
                dbHelper.createTask(sqLiteDatabase, content);
                finish();
            }
        });
    }
}
