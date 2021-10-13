package com.bruijnes.studenttracker.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private ZonedDateTime dateRequested;
    private ZonedDateTime datePaid;
}
