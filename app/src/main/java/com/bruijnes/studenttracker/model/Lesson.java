package com.bruijnes.studenttracker.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson implements Serializable {
    String lessonId;
    String date;
    List<Student> students;
}
