package com.bruijnes.studenttracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.LessonService;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import lombok.Getter;

@Getter
public class StudentLessonAdapter extends FirebaseRecyclerAdapter<Student, StudentLessonAdapter.StudentViewHolder> {

    private final LessonService lessonService = LessonService.getInstance();

    public StudentLessonAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i, @NonNull Student student) {
       CheckedTextView studentPresence = studentViewHolder.studentPresence;

       studentPresence.setText(student.fullName());
       studentPresence.setOnClickListener(view -> {
            studentPresence.setChecked(!studentPresence.isChecked());
            if(studentPresence.isChecked()) {
                studentPresence.setCheckMarkDrawable(R.drawable.ic_checkmark);
                lessonService.getStudentsInLesson().add(student);
            } else {
                studentPresence.setCheckMarkDrawable(null);
                lessonService.getStudentsInLesson().remove(student);
            }
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.student_lesson_row, parent, false);
            lessonService.getStudentsInLesson().clear();
            return new StudentViewHolder(view);
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView studentPresence;
        public StudentViewHolder(View studentView) {
            super(studentView);
            studentPresence = studentView.findViewById(R.id.studentPresence);
        }
    }

}
