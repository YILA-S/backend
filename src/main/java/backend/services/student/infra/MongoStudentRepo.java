package backend.services.student.infra;

import backend.services.student.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoStudentRepo extends MongoRepository<StudentModel, String>{
    StudentModel findByEmail(String email);
}
