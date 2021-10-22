package com.bruijnes.studenttracker.service;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bruijnes.studenttracker.model.Lesson;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LessonService extends FirebaseService {

    private static final String LESSON_KEY = "/lesson/";
    private final DatabaseReference lessonRef;
    private static final List<Lesson> lessonList = new ArrayList<>();

    public LessonService() {
        super();
        this.lessonRef = super.getDbRef(LESSON_KEY);
        lessonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lessonList.clear();
                for (DataSnapshot lessonSnapshot : snapshot.getChildren()) {
                    lessonList.add(lessonSnapshot.getValue(Lesson.class));
                }
                Log.d(TAG, "onDataChange: " + lessonList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public DatabaseReference getLessonRef() {
        return lessonRef;
    }

    public void saveLessonToDatabase(Lesson lesson) {
        String userId = lessonRef.push().getKey();
        lesson.setLessonId(userId);
        lessonRef
                .child(lesson.getLessonId())
                .setValue(lesson);
    }

    public void updateLesson(Lesson lesson) {
        lessonRef.child(lesson.getLessonId()).setValue(lesson);
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public Lesson findLessonById(String lessonId) {
        return lessonList.stream().filter(lesson -> lesson.getLessonId().equals(lessonId)).findFirst().orElse(null);
    }
}
