package com.bruijnes.studenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bruijnes.studenttracker.adapter.StudentAdapter;
import com.bruijnes.studenttracker.model.Student;
import com.bruijnes.studenttracker.service.StudentService;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class StudentFragment extends Fragment {

    public StudentFragment() {
        // Required empty public constructor
    }

    private final StudentService studentService = new StudentService();
    private StudentAdapter studentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(studentService.getStudentRef(), Student.class)
                .build();

        studentAdapter = new StudentAdapter(options);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.scrollToPosition(0);
        FloatingActionButton addStudentFab = view.findViewById(R.id.floatingActionButton4);
        addStudentFab.setOnClickListener(fabView -> startActivity(new Intent(getContext(), AddStudentActivity.class)));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        studentAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        studentAdapter.stopListening();
    }

}