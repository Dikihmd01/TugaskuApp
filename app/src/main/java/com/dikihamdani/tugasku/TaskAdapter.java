package com.dikihamdani.tugasku;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<TaskModel> myList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public TaskAdapter(List<TaskModel>myList, Activity activity) {
        this.myList = myList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TaskModel tasks = myList.get(position);
        holder.textTitle.setText(tasks.getTitle());
        holder.textNote.setText(tasks.getNote());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textNote;
        CardView cardViewResult;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textNote);
            cardViewResult = itemView.findViewById(R.id.cardViewResult);
        }
    }
}
