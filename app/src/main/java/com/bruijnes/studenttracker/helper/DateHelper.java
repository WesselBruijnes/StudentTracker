package com.bruijnes.studenttracker.helper;

import com.bruijnes.studenttracker.exception.StudentTrackerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static LocalDate parseDate(String date) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(date, dtf);
        } catch (Exception e) {
            throw new StudentTrackerException("Invalid birthday, use day month year.");
        }
    }

    public static String toString(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf);
    }
}
