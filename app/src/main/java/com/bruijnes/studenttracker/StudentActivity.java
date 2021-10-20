package com.bruijnes.studenttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentAdapter;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.StudentService;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentActivity extends AppCompatActivity {

   private final StudentService studentService = new StudentService();
   private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        RecyclerView recyclerView = findViewById(R.id.studentRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(studentService.getStudentRef(), Student.class)
                .build();

        studentAdapter = new StudentAdapter(options);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.scrollToPosition(0);
        FloatingActionButton addStudentFab = findViewById(R.id.floatingActionButton);
        addStudentFab.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddStudentActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        studentAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        studentAdapter.stopListening();
    }
}
