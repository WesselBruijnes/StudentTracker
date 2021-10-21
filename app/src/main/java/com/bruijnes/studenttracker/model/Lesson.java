package com.bruijnes.studenttracker.model;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Lesson {
    String lessonId;
    @NonNull
    String date;
    List<String> studentId;
}
