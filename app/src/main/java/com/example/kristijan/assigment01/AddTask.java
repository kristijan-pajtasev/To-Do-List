package com.example.kristijan.assigment01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AddTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.i("ACTIVITY: ", "AddTask activity started");
    }
}
