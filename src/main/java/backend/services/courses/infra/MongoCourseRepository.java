package backend.services.courses.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCourseRepository extends MongoRepository<CourseModel, String> {
}
