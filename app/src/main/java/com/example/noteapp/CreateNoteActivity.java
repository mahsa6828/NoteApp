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

public class CreateNoteActivity extends AppCompatActivity {
    AppCompatEditText edtTitle,edtNote;
    AppCompatButton btnSave,btnCancel;
    NoteDatabase noteDatabase;
    Note noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        edtTitle = findViewById(R.id.edtTitle);
        edtNote = findViewById(R.id.edtText);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        noteDatabase = new NoteDatabase(CreateNoteActivity.this);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String note = edtNote.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dateAndTime=dateFormat.format(date);

                if(title.isEmpty() || note.isEmpty()){
                    edtTitle.setError("title is empty");
                    edtNote.setError("note is empty");
                }
                else
                {
                    noteModel = new Note(title,note,dateAndTime);
                    Long id=noteDatabase.insertNote(noteModel);
                    if (id>0){
                        Toast.makeText(CreateNoteActivity.this,"insert",Toast.LENGTH_LONG).show();
                        setResult(RESULT_OK);
                    }
                    else
                    {
                        Toast.makeText(CreateNoteActivity.this," not insert",Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

    }
}