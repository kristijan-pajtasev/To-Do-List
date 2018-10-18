package com.example.kristijan.assigment01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView todosList;
    private TodoListAdapter todoListAdapter;
    private ArrayList<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        todosList = (ListView)findViewById(R.id.todoList);

        toDos = new ArrayList<ToDo>();

        // todo start remove mock data
        toDos.add(new ToDo("Test 1"));
        toDos.add(new ToDo("Test 2"));
        // todo end remove mock data

        todoListAdapter = new TodoListAdapter(this, toDos);
        todosList.setAdapter(todoListAdapter);
    }
}
