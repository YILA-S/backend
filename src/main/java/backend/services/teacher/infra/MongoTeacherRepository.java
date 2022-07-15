package backend.services.teacher.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTeacherRepository  extends MongoRepository<TeacherModel, String> {
}
