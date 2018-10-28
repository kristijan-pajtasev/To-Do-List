package com.example.kristijan.assigment01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoListAdapter extends BaseAdapter {
    private ArrayList<ToDo> toDos;
    private Context context;

    static class ViewHolder {
        public TextView todoDescription;
    }

    public TodoListAdapter(Context context, ArrayList<ToDo> toDos) {
        this.toDos = toDos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return toDos.size();
    }

    @Override
    public Object getItem(int position) {
        return toDos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // todo: db item id
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todo_item, parent, false);

            viewHolder.todoDescription = (TextView)convertView.findViewById(R.id.todoDescription);

            Button deleteButton = (Button)convertView.findViewById(R.id.deleteTask);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("DELETE ACTIVITY: ", "Delete activity button clicked");

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                    alertDialog.setMessage("Delete task?");

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                        }
                    });
                    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

                    AlertDialog dialog = alertDialog.create();

                    dialog.show();
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.todoDescription.setText(toDos.get(position).getText());

        return convertView;
    }
}
