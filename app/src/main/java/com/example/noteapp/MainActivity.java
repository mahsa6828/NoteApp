package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppCompatImageView emptyImage;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NoteDatabase noteDatabase;
    List<Note> noteList;
    NoteAdapter noteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emptyImage = findViewById(R.id.emptyImg);
        floatingActionButton=findViewById(R.id.floatBtn);
        recyclerView=findViewById(R.id.rc);
        noteDatabase = new NoteDatabase(this);
        noteList = new ArrayList<>();
        //

        int count = noteDatabase.getNoteCount();
        if (count<1){
            emptyImage.setVisibility(View.VISIBLE);
        }
        else {
            emptyImage.setVisibility(View.GONE);
        }


        noteList=noteDatabase.selectAllNotes();
        noteAdapter = new NoteAdapter(MainActivity.this,noteList);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateNoteActivity.class);
                startActivityForResult(intent,1000);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
//        noteList=noteDatabase.selectAllNotes();
//        noteAdapter = new NoteAdapter(MainActivity.this,noteList);
//        recyclerView.setAdapter(noteAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1000){
            noteList=noteDatabase.selectAllNotes();
            noteAdapter = new NoteAdapter(MainActivity.this,noteList);
            recyclerView.setAdapter(noteAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

            Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();
        }
    }


}