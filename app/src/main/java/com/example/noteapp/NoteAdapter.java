package com.example.noteapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    NoteDatabase noteDatabase;
    Context context;
    List<Note> noteList;
    public NoteAdapter(Context context , List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.note_row,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        int pos = position;
        Note note = noteList.get(position);
        holder.txtTitle.setText(note.getTitle());
        holder.txtNote.setText(note.getNote());
        holder.txtTime.setText(note.getTime());
        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditNoteActivity.class);
                intent.putExtra("idNote",note.getId());
                context.startActivity(intent);
            }
        });



        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("delete");
                alert.setMessage("are you sure to delete?");
                alert.setCancelable(false);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteDatabase = new NoteDatabase(context);
                        int idDeleted=noteDatabase.deleteNote(note.getId());
                        if (idDeleted>0){
                            noteList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,getItemCount());

                            Toast.makeText(context,"delete",Toast.LENGTH_LONG).show();
                        }

                    }

                });
                alert.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert.show();




            }
        });



    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        AppCompatTextView txtTitle,txtNote,txtTime;
        AppCompatImageView imgDelete,imgUpdate;
        RelativeLayout mainRelativeLayout;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtNote = itemView.findViewById(R.id.txtNote);
            txtTime = itemView.findViewById(R.id.txtTime);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgUpdate = itemView.findViewById(R.id.imgUpdate);
            mainRelativeLayout = itemView.findViewById(R.id.relative);
            //mainRelativeLayout.setOnCreateContextMenuListener(this);

        }

//        @Override
//        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//            contextMenu.setHeaderTitle("Select The Action");
//            contextMenu.add(0, R.id.update, 0, "update");//groupId, itemId, order, title
//            contextMenu.add(0, R.id.delete, 0, "delete");
//            //menu.add(this.getAdapterPosition(),121,0,"delete");
//
//        }



    }

}
