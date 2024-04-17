package ua.maliienko.lab5.student;

import lombok.*;

import java.sql.Date;

/**
 * Клас сутність для роботи з таблицею student
 */
@Data
@Builder
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    private String gradebookNumber;

    @Override
    public String toString() {
        return "Student " + id + ": {\n" +
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "middleName: " + middleName + "\n" +
                "birthDate: " + birthDate + "\n" +
                "gradebookNumber: " + gradebookNumber + '\n' +
                "}";
    }
}
