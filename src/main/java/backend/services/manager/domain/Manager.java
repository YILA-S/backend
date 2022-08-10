package backend.services.manager.domain;

import backend.services.appuser.domain.AppUser;

import java.time.LocalDate;

public class Manager extends AppUser {

    public Manager(String firstName, String lastName, LocalDate birthDate, String email,
                   String phone, String address, String id, String password) {
        super(firstName, lastName, birthDate, email, phone, address, id, password);
    }
}
