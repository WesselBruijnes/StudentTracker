package com.bruijnes.studenttracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.activity.AddLessonActivity;
import com.bruijnes.studenttracker.adapter.LessonAdapter;
import com.bruijnes.studenttracker.helper.ActionBarHelper;
import com.bruijnes.studenttracker.model.Lesson;
import com.bruijnes.studenttracker.service.LessonService;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class LessonFragment extends Fragment {

    private final LessonService lessonService = LessonService.getInstance();
    private LessonAdapter lessonAdapter;

    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ActionBarHelper.setSubtitle(this, R.string.lesson_title);
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        setupRecyclerView(view);
        setupFab(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        lessonAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        lessonAdapter.stopListening();
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.lessonRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Lesson> options = new FirebaseRecyclerOptions.Builder<Lesson>()
                .setQuery(lessonService.getLessonRef(), Lesson.class)
                .build();

        lessonAdapter = new LessonAdapter(options);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.scrollToPosition(0);
    }

    private void setupFab(View view) {
        FloatingActionButton addLessonFab = view.findViewById(R.id.addLessonFab);
        addLessonFab.setOnClickListener(fabView -> startActivity(new Intent(getContext(), AddLessonActivity.class)));
    }

    private void setActionBarTitle() {
    }
}