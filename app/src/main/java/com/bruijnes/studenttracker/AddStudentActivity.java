package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.model.Student;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AddStudentActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText dateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        dateOfBirth = findViewById(R.id.dateOfBirth);

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            try {

                Student student = Student.builder()
                        .firstName(firstName.getText().toString())
                        .lastName(lastName.getText().toString())
                        .phoneNumber(phoneNumber.getText().toString())
                        .dateOfBirth(parseDate())
                        .createdAt(ZonedDateTime.now())
                        .build();
            Snackbar.make(view, student.toString(), 2000).show();
            // TODO: Save to firebase
            } catch (Exception e) {
                Log.e(TAG, "onCreate: ", e);
                Snackbar.make(view, "Something went wrong adding student", 3000).show();
            }
        });

    }

    private LocalDate parseDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateOfBirth.getText().toString(), dtf);
    }

}