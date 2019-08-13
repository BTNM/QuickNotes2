package com.btdeveloper.quicknotes2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> noteList = new ArrayList<>();

    private OnItemClickListener listener;

    //define comparison logic
//    public NoteAdapter() {
//        super(DIFF_CALLBACK);
//    }
//
//    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
//        @Override
//        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
//            //same entry for id is the primary key
//            return oldItem.getId() == newItem.getId();
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
//
//            return oldItem.getTitle().equals(newItem.getTitle()) &&
//                    oldItem.getDescription().equals(newItem.getDescription()) &&
//                    oldItem.getPriority() == newItem.getPriority();
//        }
//    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create all the single item notes in the recyclerview
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        // get the data from singe notes java object into the view of our viewholder
        Note currentNote = noteList.get(position);
//        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle() );
        holder.imageTypeIcon.setImageResource(currentNote.getTypeIconImageId());
        holder.textViewTypeIcon.setText(currentNote.getTypeIcon());
        holder.textViewDescription.setText(currentNote.getDescription() );
        holder.textViewDate.setText(currentNote.getDate() );



    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList (List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();

    }

    public Note getNoteAt (int position) {
        return noteList.get(position);
//        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView imageTypeIcon;
        private TextView textViewTypeIcon;
        private TextView textViewDescription;
        private TextView textViewDate;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            imageTypeIcon = itemView.findViewById(R.id.image_typeIcon);
            textViewTypeIcon = itemView.findViewById(R.id.text_view_typeIcon);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewDate = itemView.findViewById(R.id.text_view_date);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(noteList.get(position));
//                        listener.onItemClick(getItem(position));

                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick (Note note);
    }

    public void setOnOtemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }

}