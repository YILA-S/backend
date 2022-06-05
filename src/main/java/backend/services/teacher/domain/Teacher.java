package backend.services.teacher.domain;

import backend.services.user.domain.User;

import java.time.LocalDate;

public class Teacher extends User {
    private String name;

    public Teacher(String id, String firstName, String lastName, LocalDate birthDate, String email, String phone, String address) {
        super(id, firstName, lastName, birthDate, email, phone, address);
    }
}
