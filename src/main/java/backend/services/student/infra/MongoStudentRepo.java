package backend.services.student.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoStudentRepo extends MongoRepository<StudentModel, String>{
}
