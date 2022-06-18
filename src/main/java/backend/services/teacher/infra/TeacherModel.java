package backend.services.teacher.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "teacher")
public class TeacherModel{
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
            name = "last_name",
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
