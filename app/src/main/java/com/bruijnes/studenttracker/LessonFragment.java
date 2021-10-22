package com.bruijnes.studenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.LessonAdapter;
import com.bruijnes.studenttracker.model.Lesson;
import com.bruijnes.studenttracker.service.LessonService;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class LessonFragment extends Fragment {

    private final LessonService lessonService = new LessonService();
    private LessonAdapter lessonAdapter;

    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.lessonRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Lesson> options = new FirebaseRecyclerOptions.Builder<Lesson>()
                .setQuery(lessonService.getLessonRef(), Lesson.class)
                .build();

        lessonAdapter = new LessonAdapter(options);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.scrollToPosition(0);
        FloatingActionButton addLessonFab = view.findViewById(R.id.addLessonFab);
        addLessonFab.setOnClickListener(fabView -> startActivity(new Intent(getContext(), AddStudentActivity.class)));
        return view;
    }
}