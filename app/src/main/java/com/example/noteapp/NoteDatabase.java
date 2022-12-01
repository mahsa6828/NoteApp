package com.example.noteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {
    Note note1;
    List<Note> noteList;
    public static final String db_name = "note.db";
    public static final int db_version = 1;
    public NoteDatabase(@Nullable Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "create table tbl_note(id Integer Primary key autoincrement,title text,note text,time text)";
        sqLiteDatabase.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNote(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",note.getTitle());
        contentValues.put("note",note.getNote());
        contentValues.put("time",note.getTime());
        Long id=sqLiteDatabase.insert("tbl_note",null,contentValues);
        return id;

    }

    public List<Note> selectAllNotes(){
        noteList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tbl_note",null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String title=cursor.getString(1);
            String note=cursor.getString(2);
            String time = cursor.getString(3);
            note1 = new Note(id,title,note,time);
            noteList.add(note1);
        }
        return noteList;

    }

    public Note selectNoteById(int idNote){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tbl_note where id=?",new String[]{String.valueOf(idNote)});
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String title=cursor.getString(1);
            String note=cursor.getString(2);
            String time = cursor.getString(3);
            note1 = new Note(id,title,note,time);
        }
        return note1;

    }

    public int getNoteCount(){
        noteList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from tbl_note",null);
        int count = cursor.getCount();
        return count;

    }


    public int deleteNote(int idNote){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int id=sqLiteDatabase.delete("tbl_note","id=?",new String[]{String.valueOf(idNote)});
        return id;

    }

    public int updateNote(Note note,int idNote){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",note.getTitle());
        contentValues.put("note",note.getNote());
        contentValues.put("time",note.getTime());
        int id=sqLiteDatabase.update("tbl_note",contentValues,"id=?",new String[]{String.valueOf(idNote)});
        return id;
    }
}
