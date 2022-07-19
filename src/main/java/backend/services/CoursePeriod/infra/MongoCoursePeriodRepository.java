package backend.services.CoursePeriod.infra;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCoursePeriodRepository extends MongoRepository<CoursePeriodModel, String> {
}
