package com.bruijnes.studenttracker.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    String lessonId;
    String date;
    List<Student> student;
}
