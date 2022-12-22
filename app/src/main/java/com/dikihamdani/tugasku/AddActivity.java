package com.dikihamdani.tugasku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    EditText title, note;
    Button btnSave;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.editTextTitle);
        note = findViewById(R.id.editTextNote);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTitle = title.getText().toString();
                String getNote = note.getText().toString();

                // Add data to database firebase
                // First check the input empty or not
                if (getTitle.isEmpty()) {
                    title.setError("Please fill the title field!");
                }
                else if (getNote.isEmpty()) {
                    note.setError("Please fill the not field!");
                }
                else {
                    database.child("Tasks").push().setValue(new TaskModel(getTitle, getNote)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddActivity.this, "Data has been saved!", Toast.LENGTH_LONG);
                            startActivity(new Intent(AddActivity.this, MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, "Oops! Something went wrong.", Toast.LENGTH_LONG);
                        }
                    });
                }
            }
        });
    }
}