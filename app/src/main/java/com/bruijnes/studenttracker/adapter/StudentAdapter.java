package com.bruijnes.studenttracker.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.activity.StudentInformationActivity;
import com.bruijnes.studenttracker.model.Student;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i, @NonNull Student student) {
        studentViewHolder.studentName.setText(student.fullName());
        studentViewHolder.studentName.setOnClickListener(view ->  {
            Intent intent = new Intent(view.getContext(), StudentInformationActivity.class);
            intent.putExtra("student", student);
            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.student_row, parent, false);
            return new StudentViewHolder(view);
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName;
        public StudentViewHolder(View studentView) {
            super(studentView);
            this.studentName = studentView.findViewById(R.id.studentName);
        }

    }
}
