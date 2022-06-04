package backend.services.student.infra;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class StudentModel {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    public String id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    public String firstName;

    @Column(
            name = "lats_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    public String lastName;

    @Column(
            name = "birth_date",
            nullable = false
    )
    public LocalDate birthDate;

    @Column(
            name = "email",
            unique = true
    )
    public String email;

    @Column(
            name = "phone",
            unique = true
    )
    public String phone;

    @Column(
            name = "address",
            columnDefinition = "TEXT"
    )
    public String address;
}
