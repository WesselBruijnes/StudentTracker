package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentAdapter;
import com.bruijnes.studenttracker.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        List<Student> students = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            Student x = Student.builder().build();
            x.setStudentId(i);
            x.setFirstName(i + " wessel");
            x.setLastName("bruijnes");
            x.setPhoneNumber("0681387387");
            x.setDateOfBirth(LocalDate.of(1992, 10, 31));

            students.add(x);
        }
        students.forEach(student1 -> Log.d(TAG, "onCreate: " + student1.toString()));
        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        RecyclerView recyclerView = findViewById(R.id.studentRecycleView);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.scrollToPosition(0);
        FloatingActionButton addStudentFab = findViewById(R.id.floatingActionButton);
        addStudentFab.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AddStudentActivity.class));
        });


    }
}