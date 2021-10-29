package com.bruijnes.studenttracker.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.adapter.LessonStudentAdapter;
import com.bruijnes.studenttracker.helper.ActionBarHelper;
import com.bruijnes.studenttracker.model.Lesson;
import com.bruijnes.studenttracker.model.Student;

import java.util.List;

public class LessonInformationActivity extends AppCompatActivity {

    private Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_information);

        ActionBarHelper.setSubtitle(this, R.string.lesson_detail_title);

        getLessonFromIntent();
        setupRecycleView();

    }

    private void getLessonFromIntent() {
        Intent thisIntent = getIntent();
        this.lesson = (Lesson) thisIntent.getExtras().getSerializable("lesson");
    }
    private void setupRecycleView() {
        List<Student> students = lesson.getStudents();
        RecyclerView recyclerView = findViewById(R.id.lessonStudentRecycleview);
        LessonStudentAdapter adapter = new LessonStudentAdapter(this, students);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}