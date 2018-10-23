package com.example.kristijan.assigment01;

import android.app.Activity;
import android.content.Intent;
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
    private ArrayList<ToDo> toDos;
    private Button addNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        todosList = (ListView)findViewById(R.id.todoList);
        addNewButton = (Button)findViewById(R.id.addNewTaskButton);

        toDos = new ArrayList<ToDo>();

        // todo start remove mock data
        toDos.add(new ToDo("Test 1"));
        toDos.add(new ToDo("Test 2"));
        // todo end remove mock data

        todoListAdapter = new TodoListAdapter(this, toDos);
        todosList.setAdapter(todoListAdapter);

        todosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditTask.class);
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
