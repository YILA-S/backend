package backend.services.student.infra;

import backend.services.student.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoStudentRepo extends MongoRepository<StudentModel, String>{
    Student findByEmail(String email);
}
