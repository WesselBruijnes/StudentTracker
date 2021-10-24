package com.bruijnes.studenttracker;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentLessonAdapter;
import com.bruijnes.studenttracker.model.Lesson;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.LessonService;
import com.bruijnes.studenttracker.service.StudentService;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.time.LocalDate;

public class AddLessonActivity extends AppCompatActivity {

    private final StudentService studentService = StudentService.getInstance();
    private final LessonService lessonService   = LessonService.getInstance();
    private StudentLessonAdapter studentLessonAdapter;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        RecyclerView recyclerView = findViewById(R.id.studentLessonRecyclerview);
        saveBtn = findViewById(R.id.saveLessonButton);
        cancelBtn = findViewById(R.id.cancelLessonButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(studentService.getStudentRef(), Student.class)
                .build();

        studentLessonAdapter = new StudentLessonAdapter(options);
        recyclerView.setAdapter(studentLessonAdapter);
        recyclerView.scrollToPosition(0);

        saveBtn.setOnClickListener(view -> {
            Lesson lesson = new Lesson(null, LocalDate.now().toString(), lessonService.getStudentsInLesson());
            lessonService.saveLessonToDatabase(lesson);
            finish();
        });

        cancelBtn.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        studentLessonAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        studentLessonAdapter.stopListening();
    }
}