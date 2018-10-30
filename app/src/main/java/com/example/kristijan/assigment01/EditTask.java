package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
/**
 * Activity for editing existing task. Uses dbHelper for storing task into sqlite database.
 * @author Kristijan Pajtasev
 */
public class EditTask extends Activity {
    private EditText taskContent, taskTitle;
    private CheckBox taskStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("EDIT ACTIVITY: ", "EditTask activity started");

        final DBHelper dbHelper = new DBHelper(this, "tasks.db", null, 1);
        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        final String id = intent.getExtras().getString("ID");

        Task task = dbHelper.getTask(sqLiteDatabase, id);

        taskContent = findViewById(R.id.taskContent);
        taskTitle = findViewById(R.id.taskTitle);
        taskStatus = findViewById(R.id.taskStatus);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button saveButton = findViewById(R.id.saveButton);

        taskContent.setText(task.getText());
        taskTitle.setText(task.getTitle());
        taskStatus.setChecked(task.isCompleted());

        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("EDIT ACTIVITY: ", "Cancel editing task");
                finish();
            }
        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("EDIT ACTIVITY: ", "Edit task");
                String content = taskContent.getText().toString();
                String title = taskTitle.getText().toString();
                int isCompleted = taskStatus.isChecked() ? 1 : 0;
                dbHelper.updateTask(sqLiteDatabase, id, title, content, isCompleted);
                finish();
            }
        });
    }
}
