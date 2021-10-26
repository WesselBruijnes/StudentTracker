package com.bruijnes.studenttracker.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bruijnes.studenttracker.R;
import com.bruijnes.studenttracker.helper.ActionBarHelper;

public class LessonInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_information);

        ActionBarHelper.setSubtitle(this, R.string.lesson_detail_title);

    }
}