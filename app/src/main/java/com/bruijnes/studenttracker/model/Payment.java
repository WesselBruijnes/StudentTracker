package com.bruijnes.studenttracker.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private LocalDate dateRequested;
    private LocalDate datePaid;
    private String studentId;
}
