package backend.services.inscriptions.infra;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoInscriptionRepository extends MongoRepository<InscriptionModel, String> {

    @Query("{ 'id.courseId': ?0, 'id.sectionId': ?1, 'id.coursePeriodId': ?2}")
    InscriptionModel findByInscriptionCourseAndSectionAndPeriod(String courseId, String sectionId, String period);
}
