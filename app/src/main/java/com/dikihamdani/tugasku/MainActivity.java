package com.dikihamdani.tugasku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAdd;
    TaskAdapter taskAdapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<TaskModel> taskList;
    RecyclerView table_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        table_data = findViewById(R.id.table_data);
        RecyclerView.LayoutManager myLayout = new LinearLayoutManager(this);
        table_data.setLayoutManager(myLayout);
        table_data.setItemAnimator(new DefaultItemAnimator());

        showData();
    }

    private void showData() {
        database.child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    TaskModel task = item.getValue(TaskModel.class);
                    task.setKey(item.getKey());
                    taskList.add(task);
                }
                taskAdapter = new TaskAdapter(taskList, MainActivity.this);
                table_data.setAdapter(taskAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}