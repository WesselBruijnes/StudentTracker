package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentAdapter;
import com.bruijnes.studenttracker.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        List<Student> students = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Student x = new Student();
            x.setStudentId(i);
            x.setFirstName("wessel");
            x.setLastName("bruijnes");
            students.add(x);
        }
        students.forEach(student1 -> Log.d(TAG, "onCreate: " + student1.toString()));
        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        RecyclerView view = findViewById(R.id.studentRecycleView);
        view.setAdapter(studentAdapter);
        view.setLayoutManager(new LinearLayoutManager(this));

    }
}