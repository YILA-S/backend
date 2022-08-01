package backend.services.teacher.domain;

import backend.services.appuser.domain.AppUser;

import java.time.LocalDate;

public class Teacher extends AppUser {
    private String name;

    public Teacher(String id, String firstName, String lastName, LocalDate birthDate,
                   String email, String phone, String address, String password) {
        super(firstName, lastName, birthDate, email, phone, address, id, password);
    }
}
