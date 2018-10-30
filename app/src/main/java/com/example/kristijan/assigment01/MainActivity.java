package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Activity used when starting application. Creates first database connection and sets list view.
 * @author Kristijan Pajtasev
 */
public class MainActivity extends Activity {

    private ListView tasksList;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button pendingButton, completedButton;
    private String taskStatus = "PENDING";

    private OnClickListener addNewTaskListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);
        }
    };

    private OnClickListener pendingTasksListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!taskStatus.equals("PENDING")) {
                taskStatus = "PENDING";
                pendingButton.setBackgroundResource(R.drawable.pending_button_selected);
                completedButton.setBackgroundResource(R.drawable.completed_button);
                setTasksList();
            }
        }
    };

    private OnClickListener completedTasksListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!taskStatus.equals("COMPLETED")) {
                taskStatus = "COMPLETED";
                pendingButton.setBackgroundResource(R.drawable.pending_button);
                completedButton.setBackgroundResource(R.drawable.completed_button_selected);
                setTasksList();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_main);

        dbHelper = new DBHelper(this, "tasks.db", null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        tasksList = findViewById(R.id.todoList);
        Button addNewButton = findViewById(R.id.addNewTaskButton);

        setTasksList();

        addNewButton.setOnClickListener(addNewTaskListener);

        pendingButton = findViewById(R.id.pendingTasksButton);
        pendingButton.setBackgroundResource(R.drawable.pending_button_selected);
        pendingButton.setOnClickListener(pendingTasksListener);

        completedButton = findViewById(R.id.completedTasksButton);
        completedButton.setOnClickListener(completedTasksListener);
    }

    /**
     * Fetches tasks from database and re-initializes task list.
     */
    private void setTasksList() {
        final ArrayList<Task> tasks;
        if(taskStatus.equals("PENDING"))
            tasks =  dbHelper.getTasks(sqLiteDatabase);
        else
            tasks =  dbHelper.getCompletedTasks(sqLiteDatabase);
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, tasks);
        tasksList.setAdapter(taskListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTasksList();
    }
}
