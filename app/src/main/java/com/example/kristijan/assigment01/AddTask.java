package com.example.kristijan.assigment01;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Activity for creating new task. Uses dbHelper for storing task into sqlite database.
 * @author Kristijan Pajtasev
 */
public class AddTask extends Activity {
    EditText taskContent, taskTitle;
    CheckBox taskStatus;
    String invalidInputMessage = "Title and description are required.";
    String invalidTitleMessage = "Title needs to be unique.";

    private void showToast(String message) {
        final Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("ADD ACTIVITY: ", "AddTask activity started");

        final DBHelper dbHelper = new DBHelper(this, "tasks.db", null, 1);
        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        taskContent = findViewById(R.id.taskContent);
        taskTitle = findViewById(R.id.taskTitle);
        taskStatus = findViewById(R.id.taskStatus);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button saveButton = findViewById(R.id.saveButton);

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
                String title = taskTitle.getText().toString();
                if(content.equals("") || title.equals("")) {
                    showToast(invalidInputMessage);
                } else {
                    int isCompleted = taskStatus.isChecked() ? 1 : 0;
                    boolean success = dbHelper.createTask(sqLiteDatabase, content, title, isCompleted);
                    if(success) finish();
                    else showToast(invalidTitleMessage);
                }
            }
        });
    }
}
