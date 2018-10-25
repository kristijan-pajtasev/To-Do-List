package com.example.kristijan.assigment01;

public class ToDo {
    private String text;
    private int id;

    public ToDo(int id, String text) {
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
