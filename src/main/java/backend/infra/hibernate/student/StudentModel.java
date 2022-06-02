package backend.infra.hibernate.student;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Student")
public class StudentModel {
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public String email;
    public String phone;
    public String address;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
