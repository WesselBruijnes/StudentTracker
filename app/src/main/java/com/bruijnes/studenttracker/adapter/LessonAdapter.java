package com.bruijnes.studenttracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.model.Lesson;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class LessonAdapter extends FirebaseRecyclerAdapter<Lesson, com.bruijnes.studenttracker.adapter.LessonAdapter.LessonViewHolder> {

    public LessonAdapter(@NonNull FirebaseRecyclerOptions<Lesson> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LessonViewHolder lessonViewHolder, int i, @NonNull Lesson lesson) {
        lessonViewHolder.lessonDate.setText(lesson.getDate());
//        lessonViewHolder.lessonDate.setOnClickListener(view -> {
//                Intent intent = new Intent(view.getContext(), StudentInformation.class);
//                intent.putExtra("student", Lesson);
//                view.getContext().startActivity(intent);
//        });
    }

    @NonNull
    @Override
    public LessonAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lesson_row, parent, false);
        return new LessonViewHolder(view);
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView lessonDate;

        public LessonViewHolder(View lessonView) {
            super(lessonView);
            this.lessonDate = lessonView.findViewById(R.id.lessonDate);
        }

    }
}
