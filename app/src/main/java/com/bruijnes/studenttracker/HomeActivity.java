package com.bruijnes.studenttracker;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    StudentFragment studentFragment = new StudentFragment();


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
                Log.d(TAG, "onNavigationItemSelected: gets hier!");
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, studentFragment).commit();
        }
        return false;
    }
}