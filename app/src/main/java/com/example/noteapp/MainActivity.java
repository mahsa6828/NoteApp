package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    NoteDatabase noteDatabase;
    List<Note> noteList;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton=findViewById(R.id.floatBtn);
        recyclerView=findViewById(R.id.rc);
        noteDatabase = new NoteDatabase(this);
        noteList = new ArrayList<>();
        //registerForContextMenu(recyclerView);


        noteList=noteDatabase.selectAllNotes();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateNoteActivity.class);
                startActivityForResult(intent,122);

            }
        });

        noteAdapter = new NoteAdapter(MainActivity.this,noteList);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 122){
            noteList=noteDatabase.selectAllNotes();
            noteAdapter = new NoteAdapter(MainActivity.this,noteList);
            recyclerView.setAdapter(noteAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

            Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_LONG).show();
        }
    }
    //    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.update:{
//            }
//            case R.id.delete:{
//
//            }
//        }
//        return super.onContextItemSelected(item);
//    }
}