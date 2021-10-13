package com.bruijnes.studenttracker.model;

import lombok.Data;

@Data
public class Student {

    private int studentId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    
}
