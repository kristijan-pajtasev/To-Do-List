package com.example.kristijan.assigment01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EditTask extends Activity {
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("EDIT ACTIVITY: ", "EditTask activity started");

        Button cancelButton = (Button)findViewById(R.id.cancelButton);
        Button saveButton = (Button)findViewById(R.id.saveButton);

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
                Log.i("ADD ACTIVITY: ", "Save new task");
            }
        });
    }
}
