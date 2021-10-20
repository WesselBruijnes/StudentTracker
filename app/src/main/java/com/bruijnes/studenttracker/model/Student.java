package com.bruijnes.studenttracker.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Student implements Serializable {
    private String studentId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phoneNumber;

    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
