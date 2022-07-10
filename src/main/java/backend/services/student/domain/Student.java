package backend.services.student.domain;

import backend.services.user.domain.User;

import java.time.LocalDate;

public class Student extends User {

    public Student(String id, String firstName, String lastName, LocalDate birthDate, String email, String phone, String address) {
        super(id, firstName, lastName, birthDate, email, phone, address);
    }
}
