package com.example.kristijan.assigment01;

/**
 * @class Task
 * @author Kristijan Pajtasev
 * Model class for database and list view.
 */
public class Task {
    private String text;
    private int id;

    public Task(int id, String text) {
        this.text = text;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
