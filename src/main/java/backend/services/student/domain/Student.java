package backend.services.student.domain;

import backend.services.appuser.domain.AppUser;

import java.time.LocalDate;

public class Student extends AppUser {

    public Student(String id, String firstName, String lastName, LocalDate birthDate,
                   String email, String phone, String address, String password) {
        super(firstName, lastName, birthDate, email, phone, address, id, password);
    }
}
