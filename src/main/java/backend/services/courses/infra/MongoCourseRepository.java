package backend.services.courses.infra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoCourseRepository extends MongoRepository<CourseModel, String> {
    @Query("{ 'id': ?0, 'sections.id': ?1}")
    CourseModel findByCourseIdAndBySection(String courseId, String sectionId);
}
