package com.bruijnes.studenttracker.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student  implements Serializable {
    private int studentId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private List<Payment> payments;
    private List<Date>  presence;
    private ZonedDateTime createdAt;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
