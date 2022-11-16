package com.example.noteapp;

public class Note {
    private int id;
    private String title;
    private String note;
    private String time;

    public Note(){

    }

    public Note(int id,String title,String note,String time){
        this.id = id;
        this.title = title;
        this.note = note;
        this.time=time;
    }

    public Note(String title,String note,String time){
        this.title = title;
        this.note = note;
        this.time = time;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
