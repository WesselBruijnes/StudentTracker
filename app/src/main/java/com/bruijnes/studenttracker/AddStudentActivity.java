package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.exception.StudentTrackerException;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.StudentService;
import com.google.android.gms.common.util.Strings;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class AddStudentActivity extends AppCompatActivity {

    private StudentService studentService;

    private Student editStudent;
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentService = new StudentService();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);

        setFieldsIfEdit();

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            try {
                validateFields();
                Log.i(TAG, "onCreate: " + String.valueOf(editStudent == null));
                if (editStudent == null) {
                    createNewStudent();
                } else {
                    editExistingStudent();
                }
 ;
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
        }
    }

    private void validateFields() throws StudentTrackerException{
        if (Strings.isEmptyOrWhitespace(firstName.getText().toString()) ||
                Strings.isEmptyOrWhitespace(lastName.getText().toString()) ||
                Strings.isEmptyOrWhitespace(phoneNumber.getText().toString()) ) {
            throw new StudentTrackerException("Fields cannot be empty");
        }

    }

    private void createNewStudent() {
         Student student =  new Student(firstName.getText().toString(), lastName.getText().toString(),phoneNumber.getText().toString());
         studentService.saveStudentToDatabase(student);
         super.finish();
    }

    private void editExistingStudent() {
        editStudent.setFirstName(firstName.getText().toString());
        editStudent.setLastName(lastName.getText().toString());
        editStudent.setPhoneNumber(phoneNumber.getText().toString());
        studentService.updateStudent(editStudent);
        Intent intent = new Intent();
        intent.putExtra("student", editStudent);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}