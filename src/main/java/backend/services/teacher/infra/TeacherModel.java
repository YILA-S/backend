package backend.services.teacher.infra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("teacher")
public class TeacherModel{
    @Id
    public String id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    @Indexed(unique = true)
    public String email;
    @Indexed(unique = true)
    public String phone;
    public String address;
}
