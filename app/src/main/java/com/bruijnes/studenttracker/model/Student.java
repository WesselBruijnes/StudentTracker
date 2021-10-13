package com.bruijnes.studenttracker.model;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<Payment> payments;
    private List<Date>  presence;
    private ZonedDateTime createdAt;
}
