package com.bruijnes.studenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.model.Student;

public class StudentInformation extends AppCompatActivity {

    private Student student;
    private TextView studentName;
    private TextView studentPhone;
    private Button   editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        getStudentFromIntent();

        studentName = findViewById(R.id.studentInfoName);
        studentPhone = findViewById(R.id.studentInfoPhone);
        editButton = findViewById(R.id.studentInfoEditBtn);

        studentName.setText(student.fullName());
        studentPhone.setText(student.getPhoneNumber());

        editButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
            intent.putExtra("student", student);
            startActivity(intent);
        });

    }

    private void getStudentFromIntent() {
        Intent thisIntent = getIntent();
        this.student = (Student) thisIntent.getExtras().getSerializable("student");
    }


}