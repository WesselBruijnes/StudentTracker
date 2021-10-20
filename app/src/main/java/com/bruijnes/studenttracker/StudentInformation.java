package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.model.Student;

public class StudentInformation extends AppCompatActivity {

    private Student student;
    private TextView studentName;
    private TextView studentPhone;
    private Button editButton;
    private ActivityResultLauncher<Intent> editActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        createActivityResultLauncher();
        getStudentFromIntent();

        studentName = findViewById(R.id.studentInfoName);
        studentPhone = findViewById(R.id.studentInfoPhone);
        editButton = findViewById(R.id.studentInfoEditBtn);

        setTextviews();


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
                    Log.d(TAG, "onCreate: activity is back " + result.getResultCode());
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        student = (Student) result.getData().getExtras().getSerializable("student");
                        setTextviews();
                    }
                });
    }

    private void setTextviews() {
        studentName.setText(student.fullName());
        studentPhone.setText(student.getPhoneNumber());
    }

    private void getStudentFromIntent() {
        Intent thisIntent = getIntent();
        this.student = (Student) thisIntent.getExtras().getSerializable("student");
    }


}