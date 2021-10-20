package com.bruijnes.studenttracker.service;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bruijnes.studenttracker.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentService extends FirebaseService {

    private static final String STUDENT_KEY = "/student/";
    private final DatabaseReference studentRef;
    private List<Student> studentList;

    public StudentService() {
        super();
        studentRef = super.getDbRef(STUDENT_KEY);
        studentList = new ArrayList<>();

        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for(DataSnapshot studentSnapshot : snapshot.getChildren()) {
                    studentList.add(studentSnapshot.getValue(Student.class));
                }
                Log.d(TAG, "onDataChange: " +studentList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public DatabaseReference getStudentRef() {
        return studentRef;
    }

    public void saveStudentToDatabase(Student student) {
        String userId = studentRef.push().getKey();
        student.setStudentId(userId);
        studentRef
                .child(student.getStudentId())
                .setValue(student);
    }

    public void updateStudent(Student student) {
        studentRef.child(student.getStudentId()).setValue(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
