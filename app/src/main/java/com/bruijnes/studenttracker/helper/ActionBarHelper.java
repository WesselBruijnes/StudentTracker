package com.bruijnes.studenttracker.helper;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import lombok.SneakyThrows;

public class ActionBarHelper {

    @SneakyThrows
    public static void setSubtitle(Fragment fragment, int title) {
        ((AppCompatActivity) fragment.getActivity()).getSupportActionBar().setSubtitle(title);
    }

    @SneakyThrows
    public static void setSubtitle(Activity activity, int title) {
        ((AppCompatActivity) activity).getSupportActionBar().setSubtitle(title);
    }
}