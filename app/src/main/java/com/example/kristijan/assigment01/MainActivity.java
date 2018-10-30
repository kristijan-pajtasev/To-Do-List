package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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

    private OnClickListener addNewTaskListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddTask.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        dbHelper = new DBHelper(this, "tasks.db", null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        tasksList = findViewById(R.id.todoList);
        Button addNewButton = findViewById(R.id.addNewTaskButton);

        setTasksList();

        addNewButton.setOnClickListener(addNewTaskListener);

    }

    /**
     * Fetches tasks from database and re-initializes task list.
     */
    private void setTasksList() {
        final ArrayList<Task> tasks =  dbHelper.getTasks(sqLiteDatabase);
        TaskListAdapter taskListAdapter = new TaskListAdapter(this, tasks);
        tasksList.setAdapter(taskListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTasksList();
    }
}
