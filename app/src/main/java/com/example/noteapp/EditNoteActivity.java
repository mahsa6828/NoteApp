package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity{
    NoteDatabase noteDatabase;
    AppCompatEditText edtTitle,edtNote;
    AppCompatButton btnUpdate,btnCancel;
    int id;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        edtTitle = findViewById(R.id.edtUpdateTitle);
        edtNote = findViewById(R.id.edtUpdateText);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        id=getIntent().getIntExtra("idNote",0);
        pos = getIntent().getIntExtra("pos",0);
        noteDatabase = new NoteDatabase(this);
        Note note=noteDatabase.selectNoteById(id);
        edtTitle.setText(note.getTitle());
        edtNote.setText(note.getNote());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=edtTitle.getText().toString();
                String note = edtNote.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dateAndTime=dateFormat.format(date);
                Note note1 = new Note(title,note,dateAndTime);
                int returnId=noteDatabase.updateNote(note1,id);
                if (returnId>0){
                    setResult(RESULT_OK);
                    Toast.makeText(EditNoteActivity.this,"updated",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

}