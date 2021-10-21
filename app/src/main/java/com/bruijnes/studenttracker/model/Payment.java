package com.bruijnes.studenttracker.model;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private String dateRequested;
    private String datePaid;
    private String studentId;
}
