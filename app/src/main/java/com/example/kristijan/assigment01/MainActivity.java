package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView todosList;
    private TodoListAdapter todoListAdapter;
//    private ArrayList<ToDo> toDos;
    private Button addNewButton;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        dbHelper = new DBHelper(this, "tasks.db", null, 1);
        sqLiteDatabase = dbHelper.getWritableDatabase();


//        ContentValues cv = new ContentValues();
//        cv.put("description", "some desc");
//        sqLiteDatabase.insert("tasks", null, cv);

        ArrayList<ToDo> tasks =  dbHelper.getTasks(sqLiteDatabase);

        todosList = (ListView)findViewById(R.id.todoList);
        addNewButton = (Button)findViewById(R.id.addNewTaskButton);


        todoListAdapter = new TodoListAdapter(this, tasks);
        todosList.setAdapter(todoListAdapter);

        todosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditTask.class);
                intent.putExtra("ID", 1);
                startActivity(intent);
            }
        });

        addNewButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivity(intent);
            }
        });
    }
}
