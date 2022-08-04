package backend.services.appuser.domain;

import backend.exception.InvalidParameterException;
import backend.services.role.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class AppUser {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Collection<Role> roles = new ArrayList<>();

    public AppUser(String firstName, String lastName, LocalDate birthDate, String email, String phone,
                   String address, String id, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public void addRole(Role role) throws InvalidParameterException {
        if(roles.stream().anyMatch(r -> r == role))
            throw new InvalidParameterException(String.format("Role with id: %s already set for user", role.toString()));
        roles.add(role);
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }
}
