package com.example.kristijan.assigment01;

/**
 * Model class for database and list view.
 * @author Kristijan Pajtasev
 */
public class Task {
    private String text;
    private String title;
    private int id;
    private boolean completed;

    public Task(int id, String title, String text, boolean completed) {
        this.title = title;
        this.text = text;
        this.id = id;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
