package backend.services.inscriptions.infra;

import backend.services.courses.infra.CourseModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoInscriptionRepository extends MongoRepository<InscriptionModel, String> {

    @Query("{ 'id.courseId': ?0, 'id.sectionId': ?1, 'id.coursePeriodId': ?2}")
    CourseModel findByInscriptionCourseAndSectionAndPeriod(String courseId, String sectionId, String period);
}
