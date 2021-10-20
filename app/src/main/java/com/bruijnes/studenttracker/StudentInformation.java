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
    private TextView studentDob;
    private Button   editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        getStudentFromIntent();

        studentName = findViewById(R.id.studentInfoName);
        studentPhone = findViewById(R.id.studentInfoPhone);
        studentDob = findViewById(R.id.studentInfoDob);
        editButton = findViewById(R.id.studentInfoEditBtn);

        studentName.setText(student.getFullName());
        studentPhone.setText(student.getPhoneNumber());
        studentDob.setText(student.getDateOfBirth().toString());

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