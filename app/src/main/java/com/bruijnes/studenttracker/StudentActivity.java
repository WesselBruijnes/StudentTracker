package com.bruijnes.studenttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentAdapter;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.StudentService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

   private final StudentService studentService = new StudentService();
   private final List<Student> students = studentService.getStudentList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        RecyclerView recyclerView = findViewById(R.id.studentRecycleView);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.scrollToPosition(0);
        FloatingActionButton addStudentFab = findViewById(R.id.floatingActionButton);
        addStudentFab.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddStudentActivity.class)));

    }



}
