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

public class EditTask extends Activity {
    private EditText taskContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("EDIT ACTIVITY: ", "EditTask activity started");

        final DBHelper dbHelper = new DBHelper(this, "tasks.db", null, 1);
        final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        final String id = intent.getExtras().getString("ID");

        ToDo task = dbHelper.getTask(sqLiteDatabase, id);

        taskContent = (EditText) findViewById(R.id.taskContent);
        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        Button saveButton = (Button)findViewById(R.id.saveButton);

        taskContent.setText(task.getText());

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
                dbHelper.updateTask(sqLiteDatabase, id, content);
                finish();
            }
        });
    }
}
