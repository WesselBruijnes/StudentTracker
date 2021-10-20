package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.exception.StudentTrackerException;
import com.bruijnes.studenttracker.helper.DateHelper;
import com.bruijnes.studenttracker.model.Student;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.snackbar.Snackbar;

import java.time.ZonedDateTime;
import java.util.Objects;

public class AddStudentActivity extends AppCompatActivity {


    private Student editStudent;
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

        setFieldsIfEdit();

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            try {
                validateFields();
                Student student = editStudent == null ? createNewStudent() : editExistingStudent();
                // TODO: Save to firebase
                super.finish();
            } catch (StudentTrackerException e) {
                Snackbar.make(view, Objects.requireNonNull(e.getMessage()), 3000).show();
            } catch (Exception e) {
                Log.e(TAG, "onCreate: ", e);
                Snackbar.make(view, "Something went wrong adding student", 3000).show();
            }
        });

        findViewById(R.id.cancelButton).setOnClickListener(view -> {
            super.finish();
        });
    }

    private void setFieldsIfEdit() {
        Intent intent = getIntent();
        if (intent.hasExtra("student")) {
            editStudent = (Student) intent.getExtras().getSerializable("student");
            firstName.setText(editStudent.getFirstName());
            lastName.setText(editStudent.getLastName());
            phoneNumber.setText(editStudent.getPhoneNumber());
            dateOfBirth.setText(DateHelper.toString(editStudent.getDateOfBirth()));
        }
    }

    private void validateFields() throws StudentTrackerException{
        if (Strings.isEmptyOrWhitespace(firstName.getText().toString()) ||
                Strings.isEmptyOrWhitespace(lastName.getText().toString()) ||
                Strings.isEmptyOrWhitespace(phoneNumber.getText().toString()) ||
                Strings.isEmptyOrWhitespace(dateOfBirth.getText().toString())) {
            throw new StudentTrackerException("Fields cannot be empty");
        }

    }

    private Student createNewStudent() {
         return Student.builder()
                .firstName(firstName.getText().toString())
                .lastName(lastName.getText().toString())
                .phoneNumber(phoneNumber.getText().toString())
                .dateOfBirth(DateHelper.parseDate(dateOfBirth.getText().toString()))
                .createdAt(ZonedDateTime.now())
                .build();
    }

    private Student editExistingStudent() {
        editStudent.setFirstName(firstName.getText().toString());
        editStudent.setLastName(lastName.getText().toString());
        editStudent.setDateOfBirth(DateHelper.parseDate(dateOfBirth.getText().toString()));
        editStudent.setPhoneNumber(phoneNumber.getText().toString());
        return editStudent;
    }

}