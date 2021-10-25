package com.bruijnes.studenttracker.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.fragment.LessonFragment;
import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.fragment.StudentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    StudentFragment studentFragment = new StudentFragment();
    LessonFragment lessonFragment = new LessonFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.user_menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, studentFragment).commit();
                return true;
            case R.id.lesson_menu:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, lessonFragment).commit();
                return true;
        }
        return false;
    }
}