package com.bruijnes.studenttracker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.adapter.StudentPresenceAdapter;
import com.bruijnes.studenttracker.helper.ActionBarHelper;
import com.bruijnes.studenttracker.model.Lesson;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.LessonService;

import java.time.LocalDate;
import java.util.List;

public class StudentInformationActivity extends AppCompatActivity {

    private final LessonService lessonService = LessonService.getInstance();

    private Student student;
    private Button editButton;
    private ActivityResultLauncher<Intent> editActivityResultLauncher;
    private List<Lesson> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        ActionBarHelper.setSubtitle(this, R.string.student_detail_title);

        createActivityResultLauncher();
        getStudentFromIntent();
        lessons = lessonService.getLessonsForStudent(student);

        setupRecycleView();
        setUiElements();

        editButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
            intent.putExtra("student", student);
            editActivityResultLauncher.launch(intent);
        });
    }

    private void createActivityResultLauncher() {
    editActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        student = (Student) result.getData().getExtras().getSerializable("student");
                        setUiElements();
                    }
                });
    }

    private void setUiElements() {
        TextView studentName = findViewById(R.id.studentInfoName);
        TextView studentPhone = findViewById(R.id.studentInfoPhone);
        editButton = findViewById(R.id.studentInfoEditBtn);
        TextView lessonAmountMonth = findViewById(R.id.lessonThisMonth);
        TextView lessonAmountTotal = findViewById(R.id.amountOflessons);

        studentName.setText(student.fullName());
        studentPhone.setText(student.getPhoneNumber());
        lessonAmountMonth.setText(String.format("%s %s", getString(R.string.lesson_amount_month), calculateLessonThisMonth()));
        lessonAmountTotal.setText(String.format("%s %s",getString(R.string.lesson_amount_total), lessons.size()));

    }

    private long calculateLessonThisMonth() {
        LocalDate date = LocalDate.now();
        String dateStr = String.format("%s-%s", date.getYear(), date.getMonthValue());
        return lessons.stream().filter(lesson -> lesson.getDate().contains(dateStr)).count();
    }

    private void getStudentFromIntent() {
        Intent thisIntent = getIntent();
        this.student = (Student) thisIntent.getExtras().getSerializable("student");
    }

    private void setupRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.studentPresenceRecycleView);
        StudentPresenceAdapter adapter = new StudentPresenceAdapter(this, lessons);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}