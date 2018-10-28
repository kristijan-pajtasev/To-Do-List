package com.example.kristijan.assigment01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoListAdapter extends BaseAdapter {
    private ArrayList<Task> tasks;
    private Context context;

    static class ViewHolder {
        public TextView todoDescription;
    }

    public TodoListAdapter(Context context, ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todo_item, parent, false);

            viewHolder.todoDescription = convertView.findViewById(R.id.todoDescription);

            final Button deleteButton = convertView.findViewById(R.id.deleteTask);
            deleteButton.setTag(position);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("DELETE ACTIVITY: ", "Delete activity button clicked");

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                    alertDialog.setMessage("Delete task?");

                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            final DBHelper dbHelper = new DBHelper(context, "tasks.db", null, 1);
                            final SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
                            Integer itemPosition = (Integer)deleteButton.getTag();
                            dbHelper.deleteItem(sqLiteDatabase, tasks.get(position).getId());
                            tasks.remove(itemPosition);
//                            adapter.notifyDataSetChanged();
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

        viewHolder.todoDescription.setText(tasks.get(position).getText());

        return convertView;
    }
}
