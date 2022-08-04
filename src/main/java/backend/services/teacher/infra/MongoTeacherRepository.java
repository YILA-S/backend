package backend.services.teacher.infra;

import backend.services.teacher.domain.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTeacherRepository  extends MongoRepository<TeacherModel, String> {
    TeacherModel findByEmail(String email);
}
