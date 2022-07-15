package backend.services.student.infra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("student")
public class StudentModel {
    @Id
    public String id;
    @Indexed
    public String firstName;
    public String lastName;
    @Indexed
    public LocalDate birthDate;
    @Indexed(unique = true)
    public String email;
    @Indexed(unique = true)
    public String phone;
    public String address;
}
