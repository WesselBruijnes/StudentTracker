package com.bruijnes.studenttracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.model.Lesson;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StudentPresenceAdapter  extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private final Context context;
    private final List<Lesson> lessons;

    @NonNull
    @Override
    public LessonAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lesson_row, parent, false);
        return new LessonAdapter.LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.LessonViewHolder holder, int position) {
        holder.lessonDate.setText(lessons.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

}
